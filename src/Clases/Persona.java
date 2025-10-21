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

}
