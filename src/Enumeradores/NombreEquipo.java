package Enumeradores;

public enum NombreEquipo
{
    TABLA_DE_SURF("Tabla de Surf"),
    TRAJE_DE_NEOPRENE("Traje de Neoprene"),
    BODYBOARD("Bodyboard"),
    PATAS_DE_RANA("Patas de Rana");

    private final String nombreEquipo;

    NombreEquipo(String nombreEquipo)
    {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreEquipo()
    {
        return nombreEquipo;
    }
}
