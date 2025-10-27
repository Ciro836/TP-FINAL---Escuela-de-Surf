package Clases;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Persona
{
    private static int contador = 0;
    private int idInstructor;
    private int aniosExperiencia;
    private double sueldoBase;
    private final List<ClaseDeSurf> clases;

    /// CONSTRUCTORES

    public Instructor()
    {
        this.idInstructor = ++contador;
        this.aniosExperiencia = 0;
        this.sueldoBase = 0;
        this.clases = new ArrayList<>();
    }

    public Instructor(int dni, String nombre, String apellido, int edad, int numeroTel, int aniosExperiencia, double sueldoBase)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        this.idInstructor = ++contador;
        this.aniosExperiencia = aniosExperiencia;
        this.sueldoBase = sueldoBase;
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

    public void setIdInstructor(int idInstructor)
    {
        this.idInstructor = idInstructor;
    }

    public int getAniosExperiencia()
    {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia)
    {
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
        return clases;
    }

    /// METODOS

    public boolean asignarClase(ClaseDeSurf clase)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return super.toString() + " idInstructor: " + idInstructor + "| aniosExperiencia: " + aniosExperiencia + "| sueldoBase: " + sueldoBase;
    }
}
