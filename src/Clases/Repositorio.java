package Clases;

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
        return datos;
    }

    /// METODOS

    public void agregar(int clave, T valor)
    {
        datos.put(clave, valor);
    }

    public void eliminar(int clave)
    {
        datos.remove(clave);
    }

    public void mostrarTodos()
    {
        for(Map.Entry<Integer, T> entry : datos.entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public T buscarPorId(int clave)
    {
        return datos.get(clave);
    }

}
