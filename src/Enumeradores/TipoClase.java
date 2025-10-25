package Enumeradores;

public enum TipoClase
{
    GRUPAL("Grupal"),
    PARTICULAR("Particular");

    private final String tipoDeClase;

    TipoClase(String tipoDeClase)
    {
        this.tipoDeClase = tipoDeClase;
    }

    public String getTipoDeClase()
    {
        return tipoDeClase;
    }

}
