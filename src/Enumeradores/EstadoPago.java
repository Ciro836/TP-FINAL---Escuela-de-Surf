package Enumeradores;

public enum EstadoPago
{
    PENDIENTE("Pendiente"),
    REALIZADO("Realizado");

    private final String estadoDePago;

    EstadoPago(String estadoDePago)
    {
        this.estadoDePago = estadoDePago;
    }

    public String getEstadoDePago()
    {
        return estadoDePago;
    }
}
