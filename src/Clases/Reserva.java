package Clases;

public class Reserva{
    private int idReserva;
    private Alumno alumno;
    private ClaseDeSurf claseDeSurf;
    private Pago pago;

    /// CONSTRUCTORES

    public Reserva(){
        this.idReserva = -1;
        this.alumno = null; //todavía no se le asigno un alumno, con esto podemos detectar si la reserva tiene o no un alumno o clase ya asignado
        this.claseDeSurf = null; //todavía no se le asigno una clase
        this.pago = new Pago(); //se inicializa vacío
    }

    public Reserva(int idReserva, Alumno alumno, ClaseDeSurf claseDeSurf, Pago pago) {
        this.idReserva = idReserva;
        this.alumno = alumno;
        this.claseDeSurf = claseDeSurf;
        this.pago = pago;
    }

//retorna si una reserva esta completa
    public boolean esValida(){
        if(alumno != null && claseDeSurf != null && pago != null){
            return true;
        }else{
            return false;
        }
    }

    //chequeo si esta moroso
    public boolean esMoroso(){
        if(pago != null && pago.esMoroso()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Reserva [" +
                " IDReserva=" + idReserva +
                ", Alumno=" + (alumno != null ? alumno.getAlumno : "No asignado") + //esta en rojo pq no hice nada en alumno todavia
                ", Clase=" + (claseDeSurf != null ? claseDeSurf.getIdClase() : "No asignada") +
                ", Pago=" + pago +
                ", Estado=" + (esMoroso() ? "Moroso" : pago.estaPagado() ? "Pagado" : "Pendiente") +
                ']';
    }

    /// GETTERS Y SETTERS

    public int getIdReserva() {
        return idReserva;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public ClaseDeSurf getClaseDeSurf() {
        return claseDeSurf;
    }

    public void setClaseDeSurf(ClaseDeSurf claseDeSurf) {
        this.claseDeSurf = claseDeSurf;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
