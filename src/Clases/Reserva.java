package Clases;

public class Reserva
{
    private static int contador = 0;
    private final int idReserva;
    private Alumno alumno;
    private ClaseDeSurf claseDeSurf;
    private Pago pago;

    /// CONSTRUCTORES

    public Reserva()
    {
        this.idReserva = ++contador;
        this.alumno = null; //todavía no se le asigno un alumno, con esto podemos detectar si la reserva tiene o no un alumno o clase ya asignado
        this.claseDeSurf = null; //todavía no se le asigno una clase
        this.pago = new Pago(); //se inicializa vacío
    }

    public Reserva(Alumno alumno, ClaseDeSurf claseDeSurf, Pago pago)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("⚠️: El alumno no puede ser nulo");
        }
        if (claseDeSurf == null)
        {
            throw new IllegalArgumentException("⚠️: La clase de surf no puede ser nula");
        }
        if (pago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo");
        }

        this.idReserva = ++contador;
        this.alumno = alumno;
        this.claseDeSurf = claseDeSurf;
        this.pago = pago;
    }

    /// GETTERS Y SETTERS

    public int getIdReserva()
    {
        return idReserva;
    }

    public Alumno getAlumno()
    {
        return alumno;
    }

    public void setAlumno(Alumno alumno)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("⚠️: El alumno no puede ser nulo");
        }
        this.alumno = alumno;
    }

    public ClaseDeSurf getClaseDeSurf()
    {
        return claseDeSurf;
    }

    public void setClaseDeSurf(ClaseDeSurf claseDeSurf)
    {
        if (claseDeSurf == null)
        {
            throw new IllegalArgumentException("⚠️: La clase de surf no puede ser nula");
        }
        this.claseDeSurf = claseDeSurf;
    }

    public Pago getPago()
    {
        return pago;
    }

    public void setPago(Pago pago)
    {
        if (pago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo");
        }
        this.pago = pago;
    }

    /// METODOS

    //retorna si una reserva esta completa
    public boolean esValida()
    {
        return alumno != null && claseDeSurf != null && pago != null;
    }

    @Override
    public String toString()
    {
        return "Reserva [" +
                " IDReserva=" + idReserva +
                ", Alumno=" + (alumno != null ? alumno.toString() : "No asignado") +
                ", Clase=" + (claseDeSurf != null ? claseDeSurf.getIdClase() : "No asignada") +
                ", Pago=" + pago +
                ", Estado=" + (pago.estaPagado() ? "Pagado" : "Pendiente") +
                ']';
    }

    public String mostrarReservaMejorada() {
        String alumnoNombre = alumno.getNombre() + " " + alumno.getApellido();
        String claseInfo = "Clase ID: " + claseDeSurf.getIdClase();
        String estadopago = pago.getEstadoPago().toString();
        String fechaLimite = pago.getFechaLimite().toString();
        String fechaPago = (pago.getFechaPago() == null) ? "No pagó aún" : pago.getFechaPago().toString();

        return "\n──────── RESERVA #" + idReserva + " ────────" +
                "\nAlumno: " + alumnoNombre +
                "\n" + claseInfo +
                "\nEstado del pago: " + estadopago +
                "--------------------------------" +
                "\n\nPAGO" +
                "\nMonto: $" + pago.getMonto() +
                "\nFecha límite: " + fechaLimite +
                "\nFecha del pago: " + fechaPago +
                "\n─────────────────────────────\n";

    }
}
