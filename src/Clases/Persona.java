package Clases;

import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Persona implements InterfazJson
{
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String numeroTel;

    /// CONSTRUCTORES

    public Persona()
    {
        this.dni = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.numeroTel = "";
    }

    public Persona(String dni, String nombre, String apellido, int edad, String numeroTel)
    {
        if (!dni.matches("\\d{7,8}"))
        {
            throw new IllegalArgumentException("⚠️: El DNI debe tener entre 7 y 8 dígitos numéricos.");
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
        if (!numeroTel.matches("\\d{7,15}"))
        {
            throw new IllegalArgumentException("⚠️: El teléfono debe contener solo números");
        }

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroTel = numeroTel;
    }

    /// GETTERS Y SETTERS

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        if (!dni.matches("\\d{7,8}"))
        {
            throw new IllegalArgumentException("⚠️: El DNI debe tener entre 7 y 8 dígitos numéricos.");
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

    public String getNumeroTel()
    {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel)
    {
        if (!numeroTel.matches("\\d{7,15}"))
        {
            throw new IllegalArgumentException("⚠️: El teléfono debe contener solo números");
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
}
