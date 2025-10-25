package Clases;

import Enumeradores.TipoClase;
import java.time.LocalDateTime;
import java.util.Set;

public class ClaseDeSurf{
    private int idClase;
    private Instructor instructor;
    private TipoClase tipoDeClase;
    private LocalDateTime fechaHora;
    private Set<Alumno> alumnosInscriptos;
    private int cupoMax;
    private double valorClase;

    /// CONSTRUCTORES
    public ClaseDeSurf(){
        this.idClase = -1;
        this.instructor = null;
        this.tipoDeClase = null;
        this.fechaHora = null;
        this.alumnosInscriptos = null;
        this.cupoMax = 0;
    }

    public ClaseDeSurf(int idClase, Instructor instructor, TipoClase tipoDeClase, LocalDateTime fechaHora, Set<Alumno> alumnosInscriptos, int cupoMax) {
        this.idClase = idClase;
        this.instructor = instructor;
        this.tipoDeClase = tipoDeClase;
        this.fechaHora = fechaHora;
        this.alumnosInscriptos = alumnosInscriptos;
        this.cupoMax = cupoMax;
    }
    /// METODOS

    public boolean tieneCupo(){

        if (alumnosInscriptos.size() < cupoMax) {
            return true;
        }else{
            return false;
        }
    }
//con este metodo verifico:
    public boolean inscribirAlumno(Alumno alumno){
        //si es nulo devuelvo false
        if(alumno == null){
            return false;
        }
        //si ya tiene hecha una reserva devuelvo false
        if(alumnosInscriptos.contains(alumno)){
            return false;
        }
        //si no hay cupo devuelvo false
        if(!tieneCupo()){
            return false;
        }
        //si cumple con todas las verificaciones, agrego al alumno en alumnos incriptos
        alumnosInscriptos.add(alumno);
        return true;
    }


    /// GETTERS Y SETTERS
    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase)
    {
        this.idClase = idClase;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public TipoClase getTipoDeClase() {
        return tipoDeClase;
    }
    
    public void setTipoDeClase(TipoClase tipoDeClase) {
    this.tipoDeClase = tipoDeClase;
}

public LocalDateTime getFechaHora() {
    return fechaHora;
}

public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
}

public Set<Alumno> getAlumnosInscriptos() {
    return alumnosInscriptos;
}

public void setAlumnosInscriptos(Set<Alumno> alumnosInscriptos) {
    this.alumnosInscriptos = alumnosInscriptos;
}

public int getCupoMax() {
    return cupoMax;
}

public void setCupoMax(int cupoMax) {
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
}
