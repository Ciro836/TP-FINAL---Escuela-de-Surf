package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente extends Persona
{
    private static int contador = 0;
    private final int idCliente;
    private final List<Alquiler> alquileres;

    /// CONSTRUCTORES

    public Cliente()
    {
        this.idCliente = ++contador;
        this.alquileres = new ArrayList<>();
    }

    public Cliente(String dni, String nombre, String apellido, int edad, String numeroTel)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        this.idCliente = ++contador;
        this.alquileres = new ArrayList<>();
    }

    // Constructor para cargar desde JSON (recibe el ID y actualiza el contador)
    public Cliente(int idCliente, String dni, String nombre, String apellido, int edad, String numeroTel)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        this.idCliente = idCliente;
        this.alquileres = new ArrayList<>();

        if (idCliente > contador)
        {
            contador = idCliente;
        }
    }

    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public List<Alquiler> getAlquileres()
    {
        return Collections.unmodifiableList(alquileres);
    }

    /// METODOS

    public void agregarAlquiler(Alquiler alquiler)
    {
        if (alquiler == null)
        {
            throw new IllegalArgumentException("⚠️: El alquiler no puede ser nulo.");
        }
        this.alquileres.add(alquiler);
    }

    @Override
    public String toString()
    {
        return super.toString() + " idCliente: " + idCliente;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jsonObject = super.toJSON();

        try
        {
            jsonObject.put("idCliente", idCliente);

            JSONArray jArrayAlquileres = new JSONArray();
            for (Alquiler a : alquileres)
            {
                jArrayAlquileres.put(a.getIdAlquiler());
            }

            jsonObject.put("idAlquileres", jArrayAlquileres);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
