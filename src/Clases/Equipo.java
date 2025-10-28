package Clases;

import Enumeradores.NombreEquipo;

public class Equipo
{
    private static int contador = 0;
    private final int idEquipo;
    private NombreEquipo nombre;
    private double precioPorDia;
    private boolean disponible;

    /// CONSTRUCTORES

    public Equipo()
    {
        this.idEquipo = ++contador;
        this.nombre = null;
        this.precioPorDia = 0;
        this.disponible = false;
    }

    public Equipo(NombreEquipo nombre, double precioPorDia, boolean disponible)
    {
        this.idEquipo = ++contador;
        this.nombre = nombre;
        this.precioPorDia = precioPorDia;
        this.disponible = disponible;
    }

    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdEquipo()
    {
        return idEquipo;
    }

    public NombreEquipo getNombre()
    {
        return nombre;
    }

    public void setNombre(NombreEquipo nombre)
    {
        this.nombre = nombre;
    }

    public double getPrecioPorDia()
    {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia)
    {
        this.precioPorDia = precioPorDia;
    }

    public boolean isDisponible()
    {
        return disponible;
    }

    public void setDisponible(boolean disponible)
    {
        this.disponible = disponible;
    }

    /// METODOS

    public void marcarComoOcupado()
    {

    }

    public void marcarComoDisponible()
    {

    }

    public double calcularCosto(int cantDias, NombreEquipo nombreEquipo)
    {
        return 0;
    }

}
