package ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf;

public class FechaInvalidaException extends RuntimeException
{
    public FechaInvalidaException()
    {
        super("La fecha y hora de la clase no pueden ser anteriores a la fecha actual.");
    }

    public FechaInvalidaException(String message)
    {
        super(message);
    }
}
