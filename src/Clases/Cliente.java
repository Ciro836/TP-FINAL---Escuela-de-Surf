package Clases;

import Interfaces.Pagos;

public class Cliente implements Pagos
{


    @Override
    public boolean esMoroso()
    {
        return false;
    }

    @Override
    public boolean pagar(Pago pago)
    {
        return false;
    }
}
