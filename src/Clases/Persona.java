package Clases;

import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Persona implements InterfazJson<Persona>
{
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private int numeroTel;

    /// CONSTRUCTORES

    public Persona()
    {
        this.dni = 0;
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.numeroTel = 0;
    }

    public Persona(int dni, String nombre, String apellido, int edad, int numeroTel)
    {
        if (dni <= 0)
        {
            throw new IllegalArgumentException("⚠️: El DNI debe ser un número positivo.");
        }
        if (nombre == null || nombre.isEmpty())
        {
            throw new IllegalArgumentException("⚠️: El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.isEmpty())
        {
            throw new IllegalArgumentException("⚠️: El apellido no puede estar vacío.");
        }
        if (edad <= 0)
        {
            throw new IllegalArgumentException("⚠️: La edad debe ser un número positivo.");
        }
        if (numeroTel <= 0)
        {
            throw new IllegalArgumentException("⚠️: El telefono debe ser un número positivo.");
        }

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroTel = numeroTel;
    }

    /// GETTERS Y SETTERS

    public int getDni()
    {
        return dni;
    }

    public void setDni(int dni)
    {
        if (dni <= 0)
        {
            throw new IllegalArgumentException("⚠️: El DNI debe ser un número positivo.");
        }
        this.dni = dni;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        if (nombre == null || nombre.isEmpty())
        {
            throw new IllegalArgumentException("⚠️: El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        if (apellido == null || apellido.isEmpty())
        {
            throw new IllegalArgumentException("⚠️: El apellido no puede estar vacío.");
        }
        this.apellido = apellido;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        if (edad <= 0)
        {
            throw new IllegalArgumentException("⚠️: La edad debe ser un número positivo.");
        }
        this.edad = edad;
    }

    public int getNumeroTel()
    {
        return numeroTel;
    }

    public void setNumeroTel(int numeroTel)
    {
        if (numeroTel <= 0)
        {
            throw new IllegalArgumentException("⚠️: El telefono debe ser un número positivo.");
        }
        this.numeroTel = numeroTel;
    }

    /// METODOS

    @Override
    public String toString()
    {
        return " PERSONA: " + "Dni: " + dni + "| Nombre: " + nombre + "| Apellido:" + apellido
                + "| Edad: " + edad + "| Numero de Telefono: " + numeroTel;
    }

    public JSONObject toJSON()
    {
        JSONObject objeto = new JSONObject();

        try
        {
            objeto.put("dni", dni);
            objeto.put("nombre", nombre);
            objeto.put("apellido", apellido);
            objeto.put("edad", edad);
            objeto.put("numeroTel", numeroTel);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return objeto;
    }

    @Override
    public Persona fromJSON(JSONObject objeto)
    {
        return null;
    }
}
