package Enumeradores;

public enum TipoClase
{
    GRUPAL("Grupal", 100),
    PARTICULAR("Particular", 200);

    private final String tipoDeClase;
    private final double valorClase;

    TipoClase(String tipoDeClase, double valorClase)
    {
        this.tipoDeClase = tipoDeClase;
        this.valorClase = valorClase;
    }

    public String getTipoDeClase()
    {
        return tipoDeClase;
    }

    public double getValorClase()
    {
        return valorClase;
    }

}
