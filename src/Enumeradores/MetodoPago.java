package Enumeradores;

public enum MetodoPago
{
    TARJETA("Tarjeta"),
    TRANSFERENCIA("Transferencia"),
    EFECTIVO("Efectivo");

    private final String metodoDePago;

    MetodoPago(String metodoDePago)
    {
        this.metodoDePago = metodoDePago;
    }

    public String getMetodoDePago()
    {
        return metodoDePago;
    }
}
