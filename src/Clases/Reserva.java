package Clases;

import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Reserva implements InterfazJson
{
    private static int contador = 0;
    private final int idReserva;
    private Alumno alumno;
    private ClaseDeSurf claseDeSurf;
    private Pago pago;
    private boolean estaActiva;

    /// CONSTRUCTORES

    public Reserva()
    {
        this.idReserva = ++contador;
        this.alumno = null; //todavía no se le asigno un alumno, con esto podemos detectar si la reserva tiene o no un alumno o clase ya asignado
        this.claseDeSurf = null; //todavía no se le asigno una clase
        this.pago = new Pago(); //se inicializa vacío
        this.estaActiva = true;
    }

    public Reserva(Alumno alumno, ClaseDeSurf claseDeSurf)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("⚠️: El alumno no puede ser nulo");
        }
        if (claseDeSurf == null)
        {
            throw new IllegalArgumentException("⚠️: La clase de surf no puede ser nula");
        }

        this.idReserva = ++contador;
        this.alumno = alumno;
        this.claseDeSurf = claseDeSurf;
        this.pago = new Pago();
        calcularMontoTotal();
        this.estaActiva = true;
    }

    // Constructor para cargar desde JSON (recibe el ID y actualiza el contador)
    public Reserva(int idReserva, Alumno alumno, ClaseDeSurf claseDeSurf)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("⚠️: El alumno no puede ser nulo");
        }
        if (claseDeSurf == null)
        {
            throw new IllegalArgumentException("⚠️: La clase de surf no puede ser nula");
        }

        this.idReserva = idReserva;
        this.alumno = alumno;
        this.claseDeSurf = claseDeSurf;
        this.pago = new Pago();
        calcularMontoTotal();
        this.estaActiva = true;

        if (idReserva > contador)
        {
            contador = idReserva;
        }
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

    public boolean isEstaActiva()
    {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva)
    {
        this.estaActiva = estaActiva;
    }

    /// METODOS

    //retorna si una reserva esta completa
    public boolean esValida()
    {
        return alumno != null && claseDeSurf != null && pago != null;
    }

        public void mostrarReservaMejorada()
        {
            String alumnoNombre = alumno.getNombre() + " " + alumno.getApellido();
            String claseInfo = "Clase ID: " + claseDeSurf.getIdClase();
            String estadopago = pago.getEstadoPago().toString();
            String fechaLimite = pago.getFechaLimite().toString();
            String fechaPago = (pago.getFechaPago() == null) ? "No pagó aún" : pago.getFechaPago().toString();
            String estado = isEstaActiva() ? "Activa" : "Finalizada";

            System.out.println("\n──────── RESERVA #" + idReserva + " ────────" +
                    "\nAlumno: " + alumnoNombre +
                    "\n" + claseInfo +
                    "\nEstado del pago: " + estadopago +
                    "\nEstado: " + estado +
                    "\n--------------------------------" +
                    "\n\nPAGO" +
                    "\nMonto: $" + pago.getMonto() +
                    "\nFecha límite: " + fechaLimite +
                    "\nFecha del pago: " + fechaPago +
                    "\n─────────────────────────────\n"
            );

        }

    public void calcularMontoTotal()
    {
        pago.setMonto(claseDeSurf.getValorClase());
    }

    public void cancelarReserva()
    {
        if (!estaActiva)
        {
            throw new IllegalStateException("La reserva ya se encuentra cancelada.");
        }

        this.estaActiva = false;
        if (this.claseDeSurf != null)
        {
            this.claseDeSurf.setCuposOcupados(this.claseDeSurf.getCuposOcupados() - 1);
        }
        System.out.println("Reserva cancelada con éxito.");
    }

    @Override
    public String toString()
    {
        return "Reserva [" +
                " IDReserva=" + idReserva +
                ", Alumno=" + (alumno != null ? alumno.toString() : "No asignado") +
                ", Clase=" + (claseDeSurf != null ? claseDeSurf.getIdClase() : "No asignada") +
                ", Pago=" + pago +
                ", Estado=" + pago.getEstadoPago() +
                ']';
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jsonObj = new JSONObject();

        try
        {
            jsonObj.put("idReserva", idReserva);
            jsonObj.put("idAlumno", alumno != null ? alumno.getIdAlumno() : JSONObject.NULL); //compruebo que exista un id sino null en object json
            jsonObj.put("idClase", claseDeSurf != null ? claseDeSurf.getIdClase() : JSONObject.NULL); //hago lo mismo q con alumno, aca solo guardo los id referenciales, para que no haga un bucle de inf repetida
            jsonObj.put("idPago", pago != null ? pago.getIdPago() : JSONObject.NULL);
            jsonObj.put("estaActiva", estaActiva);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
