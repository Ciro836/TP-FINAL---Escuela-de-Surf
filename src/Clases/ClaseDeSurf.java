package Clases;

import Enumeradores.TipoClase;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoInvalidoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoLlenoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.FechaInvalidaException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.PagoPendienteException;
import Interfaces.InterfazJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClaseDeSurf implements InterfazJson<ClaseDeSurf>
{
    private static int contador = 0;
    private final int idClase;
    private Instructor instructor;
    private TipoClase tipoDeClase;
    private LocalDateTime fechaHora;
    private final Set<Alumno> alumnosInscriptos;
    private int cupoMax;
    private final double valorClase;

    /// CONSTRUCTORES

    public ClaseDeSurf()
    {
        this.idClase = ++contador;
        this.instructor = null;
        this.tipoDeClase = null;
        this.fechaHora = null;
        this.alumnosInscriptos = new HashSet<>();
        this.cupoMax = 1;
        valorClase = 0.0;
    }

    public ClaseDeSurf(Instructor instructor, TipoClase tipoDeClase, LocalDateTime fechaHora, int cupoMax)
    {
        if (instructor == null)
        {
            throw new IllegalArgumentException("⚠️: El instructor no puede ser nulo.");
        }
        if (tipoDeClase == null)
        {
            throw new IllegalArgumentException("⚠️: El tipo de clase no puede ser nulo.");
        }
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now()))
        {
            throw new FechaInvalidaException();
        }
        if (cupoMax <= 0)
        {
            throw new CupoInvalidoException();
        }

        this.alumnosInscriptos = new HashSet<>();
        this.idClase = ++contador;
        this.instructor = instructor;
        this.tipoDeClase = tipoDeClase;
        this.fechaHora = fechaHora;
        this.cupoMax = cupoMax;
        this.valorClase = tipoDeClase.getValorClase();
    }

    /// GETTERS Y SETTERS

    public int getIdClase()
    {
        return idClase;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        if (instructor == null)
        {
            throw new IllegalArgumentException("⚠️: El instructor no puede ser nulo.");
        }

        // asigno el nuevo instructor
        this.instructor = instructor;
    }

    public TipoClase getTipoDeClase()
    {
        return tipoDeClase;
    }

    public void setTipoDeClase(TipoClase tipoDeClase)
    {
        if (tipoDeClase == null)
        {
            throw new IllegalArgumentException("⚠️: El tipo de clase no puede ser nulo.");
        }
        this.tipoDeClase = tipoDeClase;
    }

    public LocalDateTime getFechaHora()
    {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora)
    {
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now()))
        {
            throw new FechaInvalidaException();
        }
        this.fechaHora = fechaHora;
    }

    public Set<Alumno> getAlumnosInscriptos()
    {
        return Collections.unmodifiableSet(alumnosInscriptos);
    }

    public int getCupoMax()
    {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax)
    {
        if (cupoMax <= 0)
        {
            throw new CupoInvalidoException();
        }
        this.cupoMax = cupoMax;
    }

    public double getValorClase()
    {
        return valorClase;
    }

    /// METODOS

    public boolean tieneCupo()
    {
        return alumnosInscriptos.size() < cupoMax;
    }

    public boolean inscribirAlumno(Alumno alumno)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }
        if (alumnosInscriptos.contains(alumno))
        {
            throw new IllegalStateException("El alumno ya está inscripto en esta clase.");
        }
        if (!tieneCupo())
        {
            throw new CupoLlenoException();
        }
        if (alumno.esMoroso())
        {
            throw new PagoPendienteException();
        }

        alumnosInscriptos.add(alumno);
        return true;
    }

    public boolean eliminarAlumno(Alumno alumno)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("El alumno pasado por parametros, no puede ser nulo.");
        }
        return (alumnosInscriptos.remove(alumno));//remove ya se encarga de buscar si contiene ese alumno y devuelve true si lo elimina corectamente
    }

    public void mostrarAlumnosInscriptos()
    {
        if (alumnosInscriptos.isEmpty())
        {
            System.out.println("Todavía no hay alumnos inscriptos.");
            return;
        }

        System.out.println("\n════════════════════════════════════");
        System.out.println("Alumnos inscriptos:");
        System.out.println("══════════════════════════════════════");
        System.out.println("Instructor: " + instructor.getNombre() + " " + instructor.getApellido());
        System.out.println("Tipo de clase: " + tipoDeClase.toString());
        System.out.println("Fecha: " + fechaHora.toLocalDate() + fechaHora.toLocalTime());
        System.out.println("-------------------------------------");

        for (Alumno a : alumnosInscriptos)
        {
            System.out.println("Alumno: " + a.getNombre() + " " + a.getApellido() + "ID: " + a.getIdAlumno());
        }

        System.out.println("-------------------------------------");
        System.out.println("Cupo: " + alumnosInscriptos.size() + "/" + cupoMax);
        System.out.println("══════════════════════════════════════");

    }

    @Override
    public String toString()
    {
        return " idClase: " + idClase + "| Instructor: " + instructor + "| Tipo de Clase: " + tipoDeClase + "| fecha y hora: " + fechaHora + "| cupoMax: " + cupoMax + "| valorClase: " + valorClase + " | Alumnos inscriptos: " + alumnosInscriptos;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jObj = new JSONObject();

        try
        {
            jObj.put("idClase", idClase);
            jObj.put("Instructor", instructor != null ? instructor.getIdInstructor() : JSONObject.NULL);
            jObj.put("TipoDeClase", tipoDeClase != null ? tipoDeClase.toString() : JSONObject.NULL);
            jObj.put("fechaYhora", fechaHora != null ? fechaHora.toString() : JSONObject.NULL);

            JSONArray jArrayAlumnosInscriptos = new JSONArray();
            for (Alumno alumno : alumnosInscriptos)
            {
                jArrayAlumnosInscriptos.put(alumno.getIdAlumno()); // solo tomo el id haciendo referencia a ese alumno, sino voya tomar todos los valores de alumnos y guardarlo en clase de surf, y repetir valores q ya estan en otras clases.
            }
            jObj.put("idAlumnosInscriptos", jArrayAlumnosInscriptos);

            jObj.put("cupoMax", cupoMax);
            jObj.put("valorClase", valorClase);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jObj;
    }

    @Override
    public ClaseDeSurf fromJSON(JSONObject objeto)
    {
        Instructor instructor = new Instructor().fromJSON(objeto.getJSONObject("instructor"));

        return new ClaseDeSurf(instructor,
                TipoClase.valueOf(objeto.getString("TipoDeClase").toUpperCase()),
                LocalDateTime.parse(objeto.getString("fechaYhora")),
                objeto.getInt("cupoMax"));
    }
}
