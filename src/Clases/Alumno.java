package Clases;

import Enumeradores.MetodoPago;
import Enumeradores.NivelDeSurf;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Alumno extends Persona
{
    private static int contador = 0;
    private final int idAlumno;
    private NivelDeSurf nivel;
    private int cantClasesTomadas;

    /// CONSTRUCTORES

    public Alumno()
    {
        this.idAlumno = ++contador;
        this.nivel = null;
        this.cantClasesTomadas = 0;
    }

    public Alumno(int dni, String nombre, String apellido, int edad, int numeroTel, NivelDeSurf nivel, int cantClasesTomadas)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        if (nivel == null)
        {
            throw new IllegalArgumentException("El nivel de surf del alumno no puede ser nulo.");
        }

        if (cantClasesTomadas < 0)
        {
            throw new IllegalArgumentException("La cantidad de clases tomadas no puede ser negativa.");
        }

        this.idAlumno = ++contador;
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;
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
    public JSONObject toJSON()
    {
        JSONObject jsonObj = super.toJSON();
        try
        {
            jsonObj.put("idAlumno", idAlumno);
            jsonObj.put("nivel", nivel);
            jsonObj.put("cantClasesTomadas", cantClasesTomadas);

            JSONArray jsonArray = new JSONArray();
            for (Reserva r : reservas)
            {
                jsonArray.put(r.getIdReserva());
            }
            jsonObj.put("idReservas", jsonArray);

            JSONArray jsonArrayPagos = new JSONArray();
            for (Pago p : pagos)
            {
                jsonArrayPagos.put(p.getIdPago());
            }
            jsonObj.put("idPagos", jsonArrayPagos);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
