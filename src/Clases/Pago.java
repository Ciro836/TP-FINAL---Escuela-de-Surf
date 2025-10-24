package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;
import java.time.LocalDate;

public class Pago{
    private int idPago;
    private MetodoPago metodoPago;
    private double monto;
    private LocalDate fechaLimite;
    private LocalDate fechaPago;
    private EstadoPago estadoPago;

    /// CONSTRUCTORES

    public Pago(){
        this.idPago = -1;
        this.metodoPago = null;
        this.monto = 0.0;
        this.fechaLimite = LocalDate.now().plusDays(7);
        this.fechaPago = null;
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    public Pago(int idPago, MetodoPago metodoPago, double monto,  LocalDate fechaLimite, LocalDate fechaPago, EstadoPago estadoPago){
        this.idPago = idPago;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaLimite = fechaLimite;
        this.fechaPago = null;
        this.estadoPago = EstadoPago.PENDIENTE;

    }

    ///METODOS

    //cambio el estado a pagado, asigno la fecha en se pago y el monto
    public void pagar(double monto){
        this.monto = monto;
        this.fechaPago = LocalDate.now();
        this.estadoPago = EstadoPago.REALIZADO;
    }

    //determino si el pago esta moroso
    public boolean esMoroso() {
        if (estadoPago == EstadoPago.PENDIENTE && LocalDate.now().isAfter(fechaLimite)) {
            return true;
        } else {
            return false;
        }
    }
    //determino si el pago esta pendiente
    public boolean estaPendiente() {
        if (estadoPago == EstadoPago.PENDIENTE && !LocalDate.now().isAfter(fechaLimite)) {
            return true;
        } else {
            return false;
        }
    }
    //determino si el pago esta pagado
    public boolean estaPagado() {
        if (estadoPago == EstadoPago.REALIZADO) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        String estadoActual;

        if (esMoroso()){
            estadoActual ="Moroso";
        }else if(estaPendiente()){
            estadoActual ="Pendiente";
        }else{
            estadoActual ="Pagado";
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

    /// GETTERS Y SETTERS
    public int getIdPago() {
        return idPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }
}
