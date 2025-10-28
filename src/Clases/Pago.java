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
    private LocalDate fechaLimite;
    private LocalDate fechaPago;
    private EstadoPago estadoPago;

    /// CONSTRUCTORES

    public Pago()
    {
        this.idPago = ++contador;
        this.metodoPago = null;
        this.monto = 0.0;
        this.fechaLimite = LocalDate.now().plusDays(7);
        this.fechaPago = null;
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    public Pago(MetodoPago metodoPago, double monto, LocalDate fechaLimite)
    {
        if (metodoPago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo.");
        }
        if (monto < 0.0)
        {
            throw new IllegalArgumentException("⚠️: El monto no puede ser negativo.");
        }
        if (fechaLimite == null)
        {
            throw new IllegalArgumentException("⚠️: La fecha limite no puede ser nula.");
        }

        this.idPago = ++contador;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaLimite = fechaLimite;
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

    public void setFechaLimite(LocalDate fechaLimite)
    {
        if (fechaLimite == null)
        {
            throw new IllegalArgumentException("⚠️: La fecha limite no puede ser nula.");
        }
        this.fechaLimite = fechaLimite;
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

    //determino si el pago esta pendiente
    public boolean estaPendiente()
    {
        if (estadoPago == EstadoPago.PENDIENTE && !LocalDate.now().isAfter(fechaLimite))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //determino si el pago esta pagado
    public boolean estaPagado()
    {
        if (estadoPago == EstadoPago.REALIZADO)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        String estadoActual;

        if (estaPendiente())
        {
            estadoActual = "Pendiente";
        }
        else
        {
            estadoActual = "Pagado";
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
}
