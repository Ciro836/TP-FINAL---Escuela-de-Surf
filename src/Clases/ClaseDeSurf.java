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

    /// GETTERS Y SETTERS
    public int getIdClase() {
        return idClase;
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


}
