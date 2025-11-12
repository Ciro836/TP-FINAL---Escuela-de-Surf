package ExcepcionesPersonalizadas;

public class CupoInvalidoException extends Exception
{
    public CupoInvalidoException()
    {
        super("El cupo máximo debe ser mayor a cero.");
    }

    public CupoInvalidoException(String message)
    {
        super(message);
    }
}
