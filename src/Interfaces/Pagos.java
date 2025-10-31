package Interfaces;

import Clases.Pago;
import Enumeradores.MetodoPago;

public interface Pagos
{
    public boolean esMoroso();

    public boolean pagar(Pago pago, MetodoPago metodo);
}
