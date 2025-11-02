package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;
import Enumeradores.NivelDeSurf;
import Interfaces.Pagos;
import Interfaces.ToJson;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alumno extends Persona implements Pagos, ToJson
{
    private static int contador = 0;
    private final int idAlumno;
    private NivelDeSurf nivel;
    private int cantClasesTomadas;
    private final List<Reserva> reservas;
    private final List<Pago> pagos;


    /// CONSTRUCTORES

    public Alumno()
    {
        this.idAlumno = ++contador;
        this.nivel = null;
        this.cantClasesTomadas = 0;
        this.reservas = new ArrayList<>(); //incializa vacía, para que no tire una excepcion si luego quiero agregar una reserva al alumno
        this.pagos = new ArrayList<>();
    }

    public Alumno(int dni, String nombre, String apellido, int edad, int numeroTel, NivelDeSurf nivel, int cantClasesTomadas)
    {
        if (nivel == null)
        {
            throw new IllegalArgumentException("El nivel de surf del alumno no puede ser nulo.");
        }

        if (cantClasesTomadas < 0)
        {
            throw new IllegalArgumentException("La cantidad de clases tomadas no puede ser negativa.");
        }

        super(dni, nombre, apellido, edad, numeroTel);
        this.idAlumno = ++contador;
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;
        this.reservas = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }


    /// GETTERS Y SETTERS

    public int getIdAlumno()
    {
        return idAlumno;
    }

    public NivelDeSurf getNivel()
    {
        return nivel;
    }

    public void setNivel(NivelDeSurf nivel)
    {
        if (nivel == null)
        {
            throw new IllegalArgumentException("El nivel de surf del alumno no puede ser nulo.");
        }
        this.nivel = nivel;
    }

    public int getCantClasesTomadas()
    {
        return cantClasesTomadas;
    }

    public void setCantClasesTomadas(int cantClasesTomadas)
    {
        if (cantClasesTomadas < 0)
        {
            throw new IllegalArgumentException("La cantidad de clases tomadas no puede ser negativa.");
        }
        this.cantClasesTomadas = cantClasesTomadas;
    }

    public List<Reserva> getReservas()
    {
        return Collections.unmodifiableList(reservas);
    }

    public List<Pago> getPagos()
    {
        return Collections.unmodifiableList(pagos);
    }

    /// METODOS

    public boolean reservar(ClaseDeSurf clase, MetodoPago metodo)
    {
        //llamo a metodo inscribir alumno q esta en clase de surf, con las verificaciones necesarias
        clase.inscribirAlumno(this);

        //creo un pago, que será inicializado como pendiente, y el valor le paso el precio de la clase
        Pago pago = new Pago(metodo, clase.getValorClase());

        //creo una reserva y paso los valores
        Reserva nueva = new Reserva(this, clase, pago);
        //agrego la reserva a la list
        reservas.add(nueva);
        //agrego el pago a la list
        pagos.add(pago);
        return true; //retorto true, fue guardado con exito la reserva
    }

    public boolean cancelarReserva(Reserva reserva)
    {
        if (reserva == null)
        {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        //guardo en clase, la clase reservada por el alumno
        ClaseDeSurf clase = reserva.getClaseDeSurf();
        //si no es nula, elimo el alumno de esa clase
        if (clase != null)
        {
            clase.eliminarAlumno(this);
        }
        //ahora elimino la reserva y su pago
        reservas.remove(reserva);
        pagos.remove(reserva.getPago());

        return true;
    }


    @Override
    public boolean esMoroso()
    {
        for (Pago pago : pagos)
        {
            if (pago.getEstadoPago() == EstadoPago.PENDIENTE && LocalDate.now().isAfter(pago.getFechaLimite()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean pagar(Pago pago, MetodoPago metodo)
    {
        if (pago == null)
        {
            throw new IllegalArgumentException("El pago no puede ser nulo.");
        }
        // Si el pago no está en la lista de pagos del cliente, lo agrega
        if (!this.pagos.contains(pago))
        {
            this.pagos.add(pago);
        }
        // Marca el pago como realizado
        pago.setMetodoPago(metodo);
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago(EstadoPago.REALIZADO);
        return true;
    }

    public void mostrarReservas()
    {
        if (reservas.isEmpty())
        {
            System.out.println("No tiene hecha ninguna reserva");
        }
        else
        {
            for (Reserva reserva : reservas)
            {
                System.out.println(reserva.mostrarReservaMejorada());
            }
        }
    }

    @Override
    public String toString()
    {
        return super.toString() + " IdAlumno: " + idAlumno +
                "| Nivel de surf: " + nivel +
                "| cantClasesTomadas: " + cantClasesTomadas +
                "| Cantidad de reservas: " + reservas.size();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        try{
            jsonObject.put("idAlumno", idAlumno);
            jsonObject.put("nivel", nivel);
            jsonObject.put("cantClasesTomadas", cantClasesTomadas);

            JSONArray jsonArray = new JSONArray();
            for (Reserva r : reservas) {
                jsonArray.put(r.toJSON());
            }
            jsonObject.put("reservas", jsonArray);

            JSONArray jsonArrayPagos = new JSONArray();
            for (Pago p : pagos) {
                jsonArrayPagos.put(p.toJson());
            }
            jsonObject.put("pagos", jsonArrayPagos);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
