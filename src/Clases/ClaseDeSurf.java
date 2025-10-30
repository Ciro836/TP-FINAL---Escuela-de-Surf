package Clases;

import Enumeradores.TipoClase;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoInvalidoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoLlenoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.FechaInvalidaException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.PagoPendienteException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClaseDeSurf
{
    private static int contador = 0;
    private final int idClase;
    private Instructor instructor;
    private TipoClase tipoDeClase;
    private LocalDateTime fechaHora;
    private final Set<Alumno> alumnosInscriptos;
    private int cupoMax;
    private double valorClase;

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

    public ClaseDeSurf(Instructor instructor, TipoClase tipoDeClase, LocalDateTime fechaHora, int cupoMax) throws FechaInvalidaException, CupoInvalidoException
    {
        if (instructor == null)
        {
            throw new IllegalArgumentException("El instructor no puede ser nulo.");
        }
        if (tipoDeClase == null)
        {
            throw new IllegalArgumentException("El tipo de clase no puede ser nulo.");
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
        instructor.asignarClase(this);
        this.tipoDeClase = tipoDeClase;
        this.fechaHora = fechaHora;
        this.cupoMax = cupoMax;

        //AGREGO LOS VALORES
        if (tipoDeClase == TipoClase.GRUPAL)
        {
            this.valorClase = 100;
        }
        else
        {
            this.valorClase = 200; //Valor de la clase particular
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
        this.instructor = instructor;
    }

    public TipoClase getTipoDeClase()
    {
        return tipoDeClase;
    }

    public void setTipoDeClase(TipoClase tipoDeClase)
    {
        this.tipoDeClase = tipoDeClase;
    }

    public LocalDateTime getFechaHora()
    {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora)
    {
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
        this.cupoMax = cupoMax;
    }

    public double getValorClase()
    {
        return valorClase;
    }

    public void setValorClase(double valorClase)
    {
        this.valorClase = valorClase;
    }

    /// METODOS

    public boolean tieneCupo()
    {
        return alumnosInscriptos.size() < cupoMax;
    }

    public boolean inscribirAlumno(Alumno alumno)
    {
        try
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
            System.out.println("✅ Alumno inscripto correctamente: " + alumno.getNombre());
            return true;

        }
        catch (CupoLlenoException | PagoPendienteException e)
        {
            System.out.println("❌ No se pudo inscribir el alumno: " + e.getMessage());
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            System.out.println("⚠️ Error de datos: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }

        return false;
    }

    public boolean eliminarAlumno(Alumno alumno)
    {
        try
        {
            if (alumno == null)
            {
                throw new IllegalArgumentException("El alumno pasado por parametros, no puede ser nulo.");
            }
            return (alumnosInscriptos.remove(alumno));//remove ya se encarga de buscar si contiene ese alumno y devuelve true si lo elimina corectamente
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("⚠️ Error de datos: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }
        return false;
    }

    public void mostrarAlumnosInscriptos(){
        if (alumnosInscriptos.isEmpty()){
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

        for (Alumno a: alumnosInscriptos){
            System.out.println("Alumno: " + a.getNombre() + " " + a.getApellido() + "ID: " + a.getIdAlumno());
        }

        System.out.println("-------------------------------------");
        System.out.println("Cupo: " + alumnosInscriptos.size() + "/" + cupoMax);
        System.out.println("══════════════════════════════════════");

    }

    
    @Override
    public String toString()
    {
        return " idClase: " + idClase + "| Instructor: " + instructor + "| Tipo de Clase: " + tipoDeClase + "| fecha y hora: " + fechaHora + "| cupoMax: " + cupoMax + "| valorClase: " + valorClase;
    }
}
