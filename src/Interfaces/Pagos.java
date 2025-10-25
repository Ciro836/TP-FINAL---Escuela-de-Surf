package Interfaces;

import Clases.Pago;

public interface Pagos
{
    public boolean esMoroso();

    public boolean pagar(Pago pago);
}
