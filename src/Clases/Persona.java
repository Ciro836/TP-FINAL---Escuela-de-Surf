package Clases;

public abstract class Persona
{
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private int numeroTel;

    /// CONSTRUCTORES

    public Persona()
    {
        this.dni = 0;
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.numeroTel = 0;
    }

    public Persona(int dni, String nombre, String apellido, int edad, int numeroTel)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroTel = numeroTel;
    }

    /// GETTERS Y SETTERS

    public int getDni()
    {
        return dni;
    }

    public void setDni(int dni)
    {
        this.dni = dni;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public int getNumeroTel()
    {
        return numeroTel;
    }

    public void setNumeroTel(int numeroTel)
    {
        this.numeroTel = numeroTel;
    }

    /// METODOS

    @Override
    public String toString()
    {
        return " PERSONA: " + "Dni: " + dni + "| Nombre: " + nombre + "| Apellido:" + apellido
                + "| Edad: " + edad + "| Numero de Telefono: " + numeroTel;
    }
}
