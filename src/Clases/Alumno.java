package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.NivelDeSurf;
import Interfaces.Pagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona implements Pagos
{
    private static int contador = 0;
    private int idAlumno;
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

    public void setIdAlumno(int idAlumno)
    {
        this.idAlumno = idAlumno;
    }

    public NivelDeSurf getNivel()
    {
        return nivel;
    }

    public void setNivel(NivelDeSurf nivel)
    {
        this.nivel = nivel;
    }

    public int getCantClasesTomadas()
    {
        return cantClasesTomadas;
    }

    public void setCantClasesTomadas(int cantClasesTomadas)
    {
        this.cantClasesTomadas = cantClasesTomadas;
    }

    public List<Reserva> getReservas()
    {
        return reservas;
    }

    public List<Pago> getPagos()
    {
        return pagos;
    }

    /// METODOS

    public boolean reservar(ClaseDeSurf clase)
    {
        if (clase.inscribirAlumno(this))
        { //llamo a metodo inscribir alumno q esta en clase de surf, con las verificaciones necesarias

            //si dio true, creo un pago, que será inicializado como pendiente, y el valor le paso el precio de la clase
            Pago pago = new Pago();
            pago.setMonto(clase.getValorClase());
            //creo una reserva y paso los valores
            Reserva nueva = new Reserva(this, clase, pago);
            //el alumno paga la reserva
            this.pagar(pago);
            //agrego la reserva a la list
            reservas.add(nueva);
            //agrego el pago a la list
            pagos.add(pago);
            return true; //retorto true, fue guardado con exito la reserva
        }
        return false; //directamente retorna false, porque el if dio false
    }

    public boolean cancelarReserva(Reserva reserva)
    {
        if (reserva == null)
        {
            return false;
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
    public boolean pagar(Pago pago)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return super.toString() + " IdAlumno: " + idAlumno + "| Nivel de surf: " + nivel + "| cantClasesTomadas: " + cantClasesTomadas;
    }

}
