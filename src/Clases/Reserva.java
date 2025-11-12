package Clases;

import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Reserva implements InterfazJson
{
    private static int contador = 0;
    private final int idReserva;
    private int idAlumno;
    private int idClaseDeSurf;
    private Pago pago;

    /// CONSTRUCTORES

    public Reserva()
    {
        this.idReserva = ++contador;
        this.idAlumno = -1;
        this.idClaseDeSurf = -1;
        this.pago = new Pago(); //se inicializa vacío
    }

    public Reserva(int idAlumno, int idClaseDeSurf, Pago pago)
    {
        if (pago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo");
        }

        this.idReserva = ++contador;
        this.idAlumno = idAlumno;
        this.idClaseDeSurf = idClaseDeSurf;
        this.pago = pago;
    }

    /// GETTERS Y SETTERS

    public int getIdReserva()
    {
        return idReserva;
    }

    public int getIdAlumno()
    {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno)
    {
        this.idAlumno = idAlumno;
    }

    public int getIdClaseDeSurf()
    {
        return idClaseDeSurf;
    }

    public void setIdClaseDeSurf(int idClaseDeSurf)
    {
        this.idClaseDeSurf = idClaseDeSurf;
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
        //return alumno != null && claseDeSurf != null && pago != null;
        return true;
    }

    public String mostrarReservaMejorada()
    {
        String estadopago = pago.getEstadoPago().toString();
        String fechaLimite = pago.getFechaLimite().toString();
        String fechaPago = (pago.getFechaPago() == null) ? "No pagó aún" : pago.getFechaPago().toString();

        return "\n──────── RESERVA #" + idReserva + " ────────" +
                "\nAlumno: " + idAlumno +
                "\nClase: " + idClaseDeSurf +
                "\nEstado del pago: " + estadopago +
                "\n--------------------------------" +
                "\n\nPAGO" +
                "\nMonto: $" + pago.getMonto() +
                "\nFecha límite: " + fechaLimite +
                "\nFecha del pago: " + fechaPago +
                "\n─────────────────────────────\n";

    }

    @Override
    public String toString()
    {
        return "Reserva [" +
                " IDReserva=" + idReserva +
                ", Alumno=" + idAlumno +
                ", Clase=" + idClaseDeSurf +
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
            jsonObj.put("idAlumno", idAlumno);
            jsonObj.put("idClase", idClaseDeSurf);
            jsonObj.put("idPago", pago != null ? pago.getIdPago() : JSONObject.NULL);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
