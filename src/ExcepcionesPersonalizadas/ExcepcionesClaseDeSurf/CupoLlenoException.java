package ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf;

public class CupoLlenoException extends Exception
{
    //CONSTRUCTORES

    public CupoLlenoException()
    {
        super("La clase ya alcanzó el cupo máximo de alumnos.");
    }

    public CupoLlenoException(String mensaje)
    {
        super(mensaje);
    }
}
