package Clases;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Repositorio<T>
{
    private final Map<Integer, T> datos;
    private int proximoId = 1; // genera IDs automáticos

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

    public void agregar(T valor)
    {
        datos.put(proximoId++, valor);
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
}
