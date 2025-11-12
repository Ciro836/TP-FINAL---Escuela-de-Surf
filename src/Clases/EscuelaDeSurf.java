package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;

import java.time.LocalDate;


public class EscuelaDeSurf //Clase para encargarse de la gestión de datos y lógica de negocio
{
    private final Repositorio<Instructor> repoInstructores;
    private final Repositorio<ClaseDeSurf> repoClases;
    private final Repositorio<Alumno> repoAlumnos;
    private final Repositorio<Cliente> repoClientes;
    private final Repositorio<Reserva> repoReservas;
    private final Repositorio<Equipo> repoEquipos;
    private final Repositorio<Alquiler> repoAlquileres;
    private final Repositorio<Pago> repoPagos;

    /// CONSTRUCTOR

    public EscuelaDeSurf()
    {
        this.repoInstructores = new Repositorio<>();
        this.repoClases = new Repositorio<>();
        this.repoAlumnos = new Repositorio<>();
        this.repoClientes = new Repositorio<>();
        this.repoReservas = new Repositorio<>();
        this.repoEquipos = new Repositorio<>();
        this.repoAlquileres = new Repositorio<>();
        this.repoPagos = new Repositorio<>();
    }

    /// GETTERS

    public Repositorio<Instructor> getRepoInstructores()
    {
        return repoInstructores;
    }

    public Repositorio<ClaseDeSurf> getRepoClases()
    {
        return repoClases;
    }

    public Repositorio<Alumno> getRepoAlumnos()
    {
        return repoAlumnos;
    }

    public Repositorio<Cliente> getRepoClientes()
    {
        return repoClientes;
    }

    public Repositorio<Reserva> getRepoReservas()
    {
        return repoReservas;
    }

    public Repositorio<Equipo> getRepoEquipos()
    {
        return repoEquipos;
    }

    public Repositorio<Alquiler> getRepoAlquileres()
    {
        return repoAlquileres;
    }

    public Repositorio<Pago> getRepoPagos()
    {
        return repoPagos;
    }

    /// METODOS

    public void registrarNuevoAlumno(Alumno alumno)
    {
        if (alumno == null)
        {
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        getRepoAlumnos().agregar(alumno);
    }

    public void registrarNuevoEquipo(Equipo equipo)
    {
        if (equipo == null)
        {
            throw new IllegalArgumentException("El equipo no puede ser nulo");
        }
        getRepoEquipos().agregar(equipo);
    }

    public void pagar(Pago pago, MetodoPago metodo)
    {
        pago.setMetodoPago(metodo);
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago(EstadoPago.REALIZADO);
    }
}