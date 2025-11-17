package Enumeradores;

public enum TipoClase
{
    GRUPAL("Grupal", 100, 10),
    PARTICULAR("Particular", 200, 1);

    private final String tipoDeClase;
    private final double valorClase;
    private final int cupoMaximo;

    TipoClase(String tipoDeClase, double valorClase, int cupoMaximo)
    {
        this.tipoDeClase = tipoDeClase;
        this.valorClase = valorClase;
        this.cupoMaximo = cupoMaximo;
    }

    public String getTipoDeClase()
    {
        return tipoDeClase;
    }

    public double getValorClase()
    {
        return valorClase;
    }

    public int getCupoMaximo()
    {
        return cupoMaximo;
    }
}
