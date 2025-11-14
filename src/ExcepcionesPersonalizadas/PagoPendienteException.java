package ExcepcionesPersonalizadas;

public class PagoPendienteException extends Exception
{
    //CONSTRUCORES

    public PagoPendienteException()
    {
        super("El alumno tiene un pago pendiente y no puede inscribirse.");
    }

    public PagoPendienteException(String mensaje)
    {
        super(mensaje);
    }
}
