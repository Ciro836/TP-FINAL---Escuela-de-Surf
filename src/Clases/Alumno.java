package Clases;

import Enumeradores.NivelDeSurf;
import org.json.JSONException;
import org.json.JSONObject;

public class Alumno extends Persona
{
    private static int contador = 0;
    private final int idAlumno;
    private NivelDeSurf nivel;
    private int cantClasesTomadas;

    /// CONSTRUCTORES

    public Alumno()
    {
        this.idAlumno = ++contador;
        this.nivel = null;
        this.cantClasesTomadas = 0;
    }

    public Alumno(String dni, String nombre, String apellido, int edad, String numeroTel, NivelDeSurf nivel, int cantClasesTomadas)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        if (nivel == null)
        {
            throw new IllegalArgumentException("El nivel de surf del alumno no puede ser nulo.");
        }

        if (cantClasesTomadas < 0)
        {
            throw new IllegalArgumentException("La cantidad de clases tomadas no puede ser negativa.");
        }

        this.idAlumno = ++contador;
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;
    }

    // Constructor para cargar desde JSON (recibe el ID y actualiza el contador)
    public Alumno(int idAlumno, String dni, String nombre, String apellido, int edad, String numeroTel, NivelDeSurf nivel, int cantClasesTomadas)
    {
        super(dni, nombre, apellido, edad, numeroTel);

        this.idAlumno = idAlumno; // Asignamos el ID viejo
        this.nivel = nivel;
        this.cantClasesTomadas = cantClasesTomadas;

        // Actualizamos el contador estático si el ID cargado es mayor
        // Esto evita que se repitan IDs al crear nuevos alumnos
        if (idAlumno > contador)
        {
            contador = idAlumno;
        }
    }

    /// GETTERS Y SETTERS

    public int getIdAlumno()
    {
        return idAlumno;
    }

    public NivelDeSurf getNivel()
    {
        return nivel;
    }

    public void setNivel(NivelDeSurf nivel)
    {
        if (nivel == null)
        {
            throw new IllegalArgumentException("El nivel de surf del alumno no puede ser nulo.");
        }
        this.nivel = nivel;
    }

    public int getCantClasesTomadas()
    {
        return cantClasesTomadas;
    }

    public void setCantClasesTomadas(int cantClasesTomadas)
    {
        if (cantClasesTomadas < 0)
        {
            throw new IllegalArgumentException("La cantidad de clases tomadas no puede ser negativa.");
        }
        this.cantClasesTomadas = cantClasesTomadas;
    }

    /// METODOS

    @Override
    public String toString()
    {
        return super.toString() + " IdAlumno: " + idAlumno +
                "| Nivel de surf: " + nivel +
                "| cantClasesTomadas: " + cantClasesTomadas;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jsonObj = super.toJSON();
        try
        {
            jsonObj.put("idAlumno", idAlumno);
            jsonObj.put("nivel", nivel);
            jsonObj.put("cantClasesTomadas", cantClasesTomadas);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
