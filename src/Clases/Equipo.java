package Clases;

import Enumeradores.NombreEquipo;
import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Equipo implements InterfazJson
{
    private static int contador = 0;
    private final int idEquipo;
    private NombreEquipo nombre;
    private final double precioPorDia;
    private boolean disponible;

    /// CONSTRUCTORES

    public Equipo()
    {
        this.idEquipo = ++contador;
        this.nombre = null;
        this.precioPorDia = 0;
        this.disponible = true;
    }

    public Equipo(NombreEquipo nombre)
    {
        if (nombre == null)
        {
            throw new IllegalArgumentException("⚠️: El nombre no puede ser nulo");
        }

        this.idEquipo = ++contador;
        this.nombre = nombre;
        this.precioPorDia = nombre.getValorDelEquipoPorDia();
        this.disponible = true;
    }

    // Constructor para cargar desde JSON (recibe el ID y actualiza el contador)
    public Equipo(int idEquipo, NombreEquipo nombre)
    {
        if (nombre == null)
        {
            throw new IllegalArgumentException("⚠️: El nombre no puede ser nulo");
        }

        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.precioPorDia = nombre.getValorDelEquipoPorDia();
        this.disponible = true;

        if (idEquipo > contador)
        {
            contador = idEquipo;
        }
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
        if (nombre == null)
        {
            throw new IllegalArgumentException("⚠️: El nombre no puede ser nulo");
        }
        this.nombre = nombre;
    }

    public double getPrecioPorDia()
    {
        return precioPorDia;
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

    @Override
    public String toString()
    {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre=" + nombre +
                ", precioPorDia=" + precioPorDia +
                ", disponible=" + disponible +
                '}';
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jObj = new JSONObject();

        try
        {
            jObj.put("idEquipo", idEquipo);
            jObj.put("nombre", nombre != null ? nombre.toString() : JSONObject.NULL);
            jObj.put("precioPorDia", precioPorDia);
            jObj.put("disponible", disponible);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jObj;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return idEquipo == equipo.idEquipo;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(idEquipo);
    }
}
