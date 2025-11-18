package Clases;

import Enumeradores.TipoClase;
import ExcepcionesPersonalizadas.FechaInvalidaException;
import Interfaces.InterfazJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class ClaseDeSurf implements InterfazJson
{
    private static int contador = 0;
    private final int idClase;
    private Instructor instructor;
    private TipoClase tipoDeClase;
    private LocalDateTime fechaHora;
    private final int cupoMax;
    private final double valorClase;
    private int cuposOcupados;

    /// CONSTRUCTORES

    public ClaseDeSurf()
    {
        this.idClase = ++contador;
        this.instructor = null;
        this.tipoDeClase = null;
        this.fechaHora = null;
        this.cupoMax = -1;
        valorClase = 0.0;
        this.cuposOcupados = 0;
    }

    public ClaseDeSurf(Instructor instructor, TipoClase tipoDeClase, LocalDateTime fechaHora) throws FechaInvalidaException
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

        this.idClase = ++contador;
        this.instructor = instructor;
        this.tipoDeClase = tipoDeClase;
        this.fechaHora = fechaHora;
        this.cupoMax = tipoDeClase.getCupoMaximo();
        this.valorClase = tipoDeClase.getValorClase();
    }

    // Constructor para cargar desde JSON (recibe el ID y actualiza el contador)
    public ClaseDeSurf(int idClase, Instructor instructor, TipoClase tipoDeClase, LocalDateTime fechaHora) throws FechaInvalidaException
    {
        if (instructor == null)
        {
            throw new IllegalArgumentException("⚠️: El instructor no puede ser nulo.");
        }
        if (tipoDeClase == null)
        {
            throw new IllegalArgumentException("⚠️: El tipo de clase no puede ser nulo.");
        }

        this.idClase = idClase;
        this.instructor = instructor;
        this.tipoDeClase = tipoDeClase;
        this.fechaHora = fechaHora;
        this.cupoMax = tipoDeClase.getCupoMaximo();
        this.valorClase = tipoDeClase.getValorClase();

        if (idClase > contador)
        {
            contador = idClase;
        }
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

    public void setFechaHora(LocalDateTime fechaHora) throws FechaInvalidaException
    {
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now()))
        {
            throw new FechaInvalidaException();
        }
        this.fechaHora = fechaHora;
    }

    public int getCupoMax()
    {
        return cupoMax;
    }

    public double getValorClase()
    {
        if (this.tipoDeClase == null)
        {
            return 0.0;
        }
        return this.tipoDeClase.getValorClase();
    }

    public int getCuposOcupados()
    {
        return cuposOcupados;
    }

    public void setCuposOcupados(int cuposOcupados)
    {
        if (cuposOcupados < 0 || cuposOcupados > cupoMax)
        {
            throw new IllegalArgumentException("Cantidad de cupos inválidas. ");
        }
        this.cuposOcupados = cuposOcupados;
    }

    public boolean hayCuposDisponible()
    {
        return cuposOcupados < cupoMax;
    }

    /// METODOS

    @Override
    public String toString()
    {
        return " idClase: " + idClase + "| Instructor: " + instructor + "| Tipo de Clase: " + tipoDeClase + "| fecha y hora: " + fechaHora + "| cupoMax: " + cupoMax + "| valorClase: " + valorClase;
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
            jObj.put("cupoMax", cupoMax);
            jObj.put("valorClase", valorClase);
            jObj.put("cuposOcupados", cuposOcupados);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jObj;
    }
}
