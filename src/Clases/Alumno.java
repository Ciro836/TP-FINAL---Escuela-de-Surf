package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.NivelDeSurf;
import Interfaces.Pagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona implements Pagos
{
    private int idAlumno;
    private NivelDeSurf nivel;
    private int cantClasesTomadas;
    private final List<Reserva> reservas;
    private final List<Pago> pagos;


    /// CONSTRUCTORES

    public Alumno()
    {
        this.idAlumno = -1;
        this.nivel = null;
        this.cantClasesTomadas = 0;
        this.reservas = new ArrayList<>(); //incializa vacía, para que no tire una excepcion si luego quiero agregar una reserva al alumno
        this.pagos = new ArrayList<>();
    }

    public Alumno(int idAlumno, NivelDeSurf nivel, int cantClasesTomadas, List<Reserva> reservas, List<Pago> pagos)
    {
        this.idAlumno = idAlumno;
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;
        this.reservas = reservas;
        this.pagos = pagos;
    }

    public Alumno(String dni, String nombre, String apellido, int edad, String numeroTel, int idAlumno, NivelDeSurf nivel, int cantClasesTomadas, List<Reserva> reservas, List<Pago> pagos)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        this.idAlumno = idAlumno;
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;
        this.reservas = reservas;
        this.pagos = pagos;
    }

    /// METODOS

    public boolean reservar(ClaseDeSurf clase)
    {
        if (clase.inscribirAlumno(this))
        { //llamo a metodo inscribir alumno q esta en case de surf, con las verificaciones necesarias

            //si dio true, creo un pago, que será inicializado como pendiente, y el valor le paso el precio de la clase
            Pago pago = new Pago();
            pago.setMonto(clase.getValorClase());
            //creo una reserva y paso los valores
            Reserva nueva = new Reserva(this, clase, pago);
            //agrego la reserva a la list
            reservas.add(nueva);
            //agrego el pago a la list
            pagos.add(pago);
            return true; //retorto true, fue guardado con exito la reserva
        }
        return false; //directamente retorna false, porque el if dio false
    }

    public boolean cancelarReserva()
    {
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

}
