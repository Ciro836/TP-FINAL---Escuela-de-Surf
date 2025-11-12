package ExcepcionesPersonalizadas;

public class FechaInvalidaException extends Exception
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
