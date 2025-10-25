package Enumeradores;

public enum NivelDeSurf
{
    PRINCIPIANTE("Principiante"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado");

    private final String nivelDeSurf;

    NivelDeSurf(String nivelDeSurf)
    {
        this.nivelDeSurf = nivelDeSurf;
    }

    public String getNivelDeSurf()
    {
        return nivelDeSurf;
    }
    }
