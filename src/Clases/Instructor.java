package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Instructor extends Persona
{
    private static int contador = 0;
    private final int idInstructor;
    private int aniosExperiencia;
    private double sueldoBase;
    private final List<ClaseDeSurf> clases;
    private static final double TARIFA_POR_CLASE = 50.0;

    /// CONSTRUCTORES

    public Instructor()
    {
        this.idInstructor = ++contador;
        this.aniosExperiencia = 0;
        this.sueldoBase = 10000;
        this.clases = new ArrayList<>();
    }

    public Instructor(int dni, String nombre, String apellido, int edad, int numeroTel, int aniosExperiencia)
    {
        super(dni, nombre, apellido, edad, numeroTel);

        if (aniosExperiencia < 0)
        {
            throw new IllegalArgumentException("⚠️: Los años de experiencia no puede ser negativos");
        }

        this.idInstructor = ++contador;
        this.aniosExperiencia = aniosExperiencia;
        this.sueldoBase = 10000;
        this.clases = new ArrayList<>();
    }

    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdInstructor()
    {
        return idInstructor;
    }

    public int getAniosExperiencia()
    {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia)
    {
        if (aniosExperiencia < 0)
        {
            throw new IllegalArgumentException("⚠️: Los años de experiencia no puede ser negativos");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    public double getSueldoBase()
    {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase)
    {
        if (sueldoBase < 0)
        {
            throw new IllegalArgumentException("⚠️: El sueldo no puede ser negativo.");
        }
        this.sueldoBase = sueldoBase;
    }

    public List<ClaseDeSurf> getClases()
    {
        return Collections.unmodifiableList(clases);
    }

    /// METODOS

    public void asignarClase(ClaseDeSurf clase)
    {
        if (clase == null)
        {
            throw new IllegalArgumentException("⚠️: No se puede asignar una clase nula.");
        }

        this.clases.add(clase);
    }

    public void eliminarClase(ClaseDeSurf clase)
    {
        if (clase == null)
        {
            throw new IllegalArgumentException("⚠️: La clase pasada por parametro es nula.");
        }

        this.clases.remove(clase);
    }

    public double calcularSueldo()
    {
        int cantidadDeClases = this.clases.size();

        double sueldoPorClases = cantidadDeClases * TARIFA_POR_CLASE;

        return this.sueldoBase + sueldoPorClases;
    }

    @Override
    public String toString()
    {
        return super.toString() + " idInstructor: " + idInstructor + "| aniosExperiencia: " + aniosExperiencia + "| sueldoBase: " + sueldoBase;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject objeto = super.toJSON();

        try
        {
            objeto.put("idInstructor", idInstructor);
            objeto.put("aniosExperiencia", aniosExperiencia);
            objeto.put("sueldoBase", sueldoBase);

            JSONArray arrClases = new JSONArray();
            for (ClaseDeSurf clase : this.clases)
            {
                arrClases.put(clase.getIdClase());
            }

            objeto.put("idClases", arrClases);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return objeto;
    }

    @Override
    public Instructor fromJSON(JSONObject objeto)
    {
        return new Instructor(objeto.getInt("dni"),
                objeto.getString("nombre"),
                objeto.getString("apellido"),
                objeto.getInt("edad"),
                objeto.getInt("numeroTel"),
                objeto.getInt("aniosExperiencia"));
    }
}
