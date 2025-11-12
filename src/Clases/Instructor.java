package Clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Instructor extends Persona
{
    private static int contador = 0;
    private final int idInstructor;
    private int aniosExperiencia;

    /// CONSTRUCTORES

    public Instructor()
    {
        this.idInstructor = ++contador;
        this.aniosExperiencia = 0;
    }

    public Instructor(String dni, String nombre, String apellido, int edad, String numeroTel, int aniosExperiencia)
    {
        super(dni, nombre, apellido, edad, numeroTel);

        if (aniosExperiencia < 0)
        {
            throw new IllegalArgumentException("⚠️: Los años de experiencia no puede ser negativos");
        }

        this.idInstructor = ++contador;
        this.aniosExperiencia = aniosExperiencia;
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

    /// METODOS

    @Override
    public String toString()
    {
        return super.toString() + " idInstructor: " + idInstructor + "| aniosExperiencia: " + aniosExperiencia;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject objeto = super.toJSON();

        try
        {
            objeto.put("idInstructor", idInstructor);
            objeto.put("aniosExperiencia", aniosExperiencia);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return objeto;
    }
}
