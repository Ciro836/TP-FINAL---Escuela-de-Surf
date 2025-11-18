package Clases;

import Interfaces.InterfazJson;
import org.json.JSONArray;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Repositorio<T extends InterfazJson>
{
    private final Map<Integer, T> datos;

    /// CONSTRUCTOR

    public Repositorio()
    {
        this.datos = new HashMap<>();
    }

    /// GETTERS

    public Map<Integer, T> getDatos()
    {
        return Collections.unmodifiableMap(datos);
    }


    /// METODOS

    //para obtener la coleccion de valores de los repositorios, sin el map completo. Lo utilizo en el Json.
    public Collection<T> getTodos()
    {
        return datos.values();
    }

    public void agregar(int clave, T valor)
    {
        datos.put(clave, valor);
    }

    public boolean eliminar(int clave)
    {
        if (!datos.containsKey(clave))
        {
            throw new IllegalArgumentException("No se encontro el registro con la misma clave.");
        }
        else
        {
            datos.remove(clave);
            return true;
        }
    }

    public T buscarPorId(int clave)
    {
        return datos.get(clave);
    }

    @Override
    public String toString()
    {
        return datos.toString();
    }

    public JSONArray toJSON()
    {
        JSONArray jArray = new JSONArray();
        for (Map.Entry<Integer, T> entry : datos.entrySet())
        {
            jArray.put(entry.getValue().toJSON());
        }
        return jArray;
    }
}
