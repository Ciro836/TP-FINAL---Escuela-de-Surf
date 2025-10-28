package Clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Repositorio<T>
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

    public boolean agregar(int clave, T valor)
    {
        if (datos.containsKey(clave))
        { //si ya contiene la clase, devuelvo falso. Lo verifico porque sino el put sobreescribe sobre una clave ya existente.
            return false;
        }
        datos.put(clave, valor); //sino lo agrego y devuelvo true.
        return true;
    }

    public boolean eliminar(int clave)
    {
        if (!datos.containsKey(clave))
        {
            return false;
        }
        else
        {
            datos.remove(clave);
            return true;
        }
    }

    public void mostrarTodos()
    {
        for (Map.Entry<Integer, T> entry : datos.entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
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
