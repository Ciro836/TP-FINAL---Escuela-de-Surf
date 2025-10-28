package ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf;

public class CupoInvalidoException extends RuntimeException
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
