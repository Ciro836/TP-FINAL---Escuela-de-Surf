package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;

import java.time.LocalDate;

public class Pago
{
    private static int contador = 0;
    private final int idPago;
    private MetodoPago metodoPago;
    private double monto;
    private final LocalDate fechaLimite;
    private LocalDate fechaPago;
    private EstadoPago estadoPago;

    /// CONSTRUCTORES

    public Pago()
    {
        this.idPago = ++contador;
        this.metodoPago = null;
        this.monto = 0.0;
        this.fechaLimite = LocalDate.now().plusDays(30);
        this.fechaPago = null;
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    public Pago(MetodoPago metodoPago, double monto)
    {
        if (metodoPago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo.");
        }
        if (monto < 0.0)
        {
            throw new IllegalArgumentException("⚠️: El monto no puede ser negativo.");
        }

        this.idPago = ++contador;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaLimite = LocalDate.now().plusDays(30);
        this.fechaPago = null;
        this.estadoPago = EstadoPago.PENDIENTE;

    }

    /// GETTERS Y SETTERS

    public int getIdPago()
    {
        return idPago;
    }

    public MetodoPago getMetodoPago()
    {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago)
    {
        if (metodoPago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo.");
        }
        this.metodoPago = metodoPago;
    }

    public double getMonto()
    {
        return monto;
    }

    public void setMonto(double monto)
    {
        if (monto < 0.0)
        {
            throw new IllegalArgumentException("⚠️: El monto no puede ser negativo.");
        }
        this.monto = monto;
    }

    public LocalDate getFechaLimite()
    {
        return fechaLimite;
    }

    public LocalDate getFechaPago()
    {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago)
    {
        this.fechaPago = fechaPago;
    }

    public EstadoPago getEstadoPago()
    {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago)
    {
        this.estadoPago = estadoPago;
    }

    /// METODOS

    @Override
    public String toString()
    {
        String estadoActual;

        if (estadoPago == EstadoPago.REALIZADO)
        {
            estadoActual = "Pagado";
        }
        else if (LocalDate.now().isAfter(fechaLimite)) // Si está PENDIENTE y venció la fecha
        {
            estadoActual = "Pendiente (Vencido)";
        }
        else
        {
            estadoActual = "Pendiente (Vigente)";
        }

        return "Pago [" +
                "idPago=" + idPago +
                ", Metodo de Pago=" + metodoPago +
                ", Monto=" + monto +
                ", Fecha Limite=" + fechaLimite +
                ", Fecha de Pago=" + fechaPago +
                ", Estado del Pago=" + estadoActual +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pago pago = (Pago) o;

        return idPago == pago.idPago;
    }

    @Override
    public int hashCode()
    {
        return idPago;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObj = new JSONObject();
        try {

            jsonObj.put("idPago", idPago);
            jsonObj.put("metodoPago", metodoPago != null ? metodoPago.toString() : JSONObject.NULL);
            jsonObj.put("monto", monto);
            jsonObj.put("fechaLimite", fechaLimite.toString());
            jsonObj.put("fechaPago", fechaPago != null ? fechaPago.toString() : JSONObject.NULL);
            jsonObj.put("estadoPago", estadoPago.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
