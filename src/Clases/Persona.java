package Clases;

public abstract class Persona{
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String numeroTel;

    /// CONSTRUCTORES

    public Persona(){
        this.dni = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.numeroTel = "";
    }

    public Persona(String dni, String nombre, String apellido, int edad, String numeroTel) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroTel = numeroTel;
    }

    /// MOSTRAR UNA PERSONA
    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", numeroTel='" + numeroTel + '\'' +
                '}';
    }

    /// GETTERS Y SETTERS

    public String getDni() {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
}
