package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Instructor extends Persona
{
    private static int contador = 0;
    private final int idInstructor;
    private int aniosExperiencia;
    private double sueldoBase;
    private final List<ClaseDeSurf> clases;

    /// CONSTRUCTORES

    public Instructor()
    {
        this.idInstructor = ++contador;
        this.aniosExperiencia = 0;
        this.sueldoBase = 10000;
        this.clases = new ArrayList<>();
    }

    public Instructor(int dni, String nombre, String apellido, int edad, int numeroTel, int aniosExperiencia)
    {
        if (aniosExperiencia < 0)
        {
            throw new IllegalArgumentException("⚠️: Los años de experiencia no puede ser negativos");
        }

        super(dni, nombre, apellido, edad, numeroTel);
        this.idInstructor = ++contador;
        this.aniosExperiencia = aniosExperiencia;
        this.sueldoBase = 10000;
        this.clases = new ArrayList<>();
    }

    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdInstructor()
    {
        return idInstructor;
    }

    public int getAniosExperiencia()
    {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia)
    {
        if (aniosExperiencia < 0)
        {
            throw new IllegalArgumentException("⚠️: Los años de experiencia no puede ser negativos");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    public double getSueldoBase()
    {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase)
    {
        this.sueldoBase = sueldoBase;
    }

    public List<ClaseDeSurf> getClases()
    {
        return Collections.unmodifiableList(clases);
    }

    /// METODOS

    public void asignarClase(ClaseDeSurf clase)
    {
        if (clase == null)
        {
            throw new IllegalArgumentException("⚠️: No se puede asignar una clase nula.");
        }

        this.clases.add(clase);
    }

    public double calcularSueldo()
    {
        final double TARIFA_POR_CLASE = 50.0;

        int cantidadDeClases = this.clases.size();

        double sueldoPorClases = cantidadDeClases * TARIFA_POR_CLASE;

        return this.sueldoBase + sueldoPorClases;
    }

    @Override
    public String toString()
    {
        return super.toString() + " idInstructor: " + idInstructor + "| aniosExperiencia: " + aniosExperiencia + "| sueldoBase: " + sueldoBase;
    }
}
