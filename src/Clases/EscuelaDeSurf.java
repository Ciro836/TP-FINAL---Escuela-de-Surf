package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;
import ExcepcionesPersonalizadas.IdNoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    public void registrarNuevoInstructor(Instructor instructor)
    {
        if (instructor == null)
        {
            throw new IllegalArgumentException("El instructor no puede ser nulo");
        }
        getRepoInstructores().agregar(instructor);
    }

    public void registrarNuevaClase(ClaseDeSurf clase)
    {
        if (clase == null)
        {
            throw new IllegalArgumentException("La clase no puede ser nula");
        }
        getRepoClases().agregar(clase);
    }

    public Alumno buscarAlumnoPorId(int id) throws IdNoEncontradoException
    {
        Alumno a = getRepoAlumnos().buscarPorId(id);

        if (a == null)
        {
            throw new IdNoEncontradoException("No se ha encontrado ningun alumno con el id ingresado.");
        }

        return a;
    }

    public Instructor buscarInstructorPorId(int id) throws IdNoEncontradoException
    {
        Instructor i = getRepoInstructores().buscarPorId(id);

        if (i == null)
        {
            throw new IdNoEncontradoException("No se ha encontrado ningun alumno con el id ingresado.");
        }

        return i;
    }

    public List<Reserva> buscarReservasPorAlumnoId(int idAlumno) throws IdNoEncontradoException
    {
        Alumno alumno = buscarAlumnoPorId(idAlumno);

        List<Reserva> reservasDelAlumno = new ArrayList<>();

        for (Reserva reserva : getRepoReservas().getTodos())
        {
            if (reserva.getAlumno().getIdAlumno() == alumno.getIdAlumno())
            {
                reservasDelAlumno.add(reserva);
            }
        }

        return reservasDelAlumno;
    }

    private void pagar(Pago pago, MetodoPago metodo)
    {
        pago.setMetodoPago(metodo);
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago(EstadoPago.REALIZADO);
    }

    public void pagarReserva(int idReserva, MetodoPago metodo) throws IdNoEncontradoException, IllegalStateException
    {
        Reserva reserva = getRepoReservas().buscarPorId(idReserva);
        if (reserva == null)
        {
            throw new IdNoEncontradoException("No se encontró ninguna reserva con el ID: " + idReserva);
        }

        Pago pago = reserva.getPago();
        if (pago.getEstadoPago() == EstadoPago.REALIZADO)
        {
            throw new IllegalStateException("Esta reserva ya se encuentra pagada.");
        }

        pagar(pago, metodo);
    }

    public void pagarAlquiler(int idAlquiler, MetodoPago metodo) throws IdNoEncontradoException, IllegalStateException
    {
        Alquiler alquiler = getRepoAlquileres().buscarPorId(idAlquiler);
        if (alquiler == null)
        {
            throw new IdNoEncontradoException("No se encontró ningún alquiler con el ID: " + idAlquiler);
        }

        Pago pago = alquiler.getPago();
        if (pago.getEstadoPago() == EstadoPago.REALIZADO)
        {
            throw new IllegalStateException("Este alquiler ya se encuentra pagado.");
        }

        pagar(pago, metodo);
    }

    public boolean chequearMorosidadAlumno(int idAlumno) throws IdNoEncontradoException
    {
        List<Reserva> reservas = buscarReservasPorAlumnoId(idAlumno);

        for (Reserva r : reservas)
        {
            if (r.getPago().esMoroso())
            {
                return true;
            }
        }

        return false; // Si termina el bucle, significa que no encontró pagos morosos
    }

    public boolean chequearMorosidadCliente(int idCliente) throws IdNoEncontradoException
    {
        Cliente cliente = getRepoClientes().buscarPorId(idCliente);
        if (cliente == null)
        {
            throw new IdNoEncontradoException("No se encontró ningún cliente con el ID: " + idCliente);
        }

        for (Alquiler a : cliente.getAlquileres())
        {
            if (a.getPago().esMoroso())
            {
                return true;
            }
        }

        return false;
    }

    public List<Alumno> mostrarAlumnosDeUnaClase(int idClase) throws IdNoEncontradoException
    {
        ClaseDeSurf clase = getRepoClases().buscarPorId(idClase);
        if (clase == null)
        {
            throw new IdNoEncontradoException("No fue encontrada ninguna clase con ese ID: " + idClase);
        }

        List<Alumno> arrAlumnos = new ArrayList<>();

        for (Reserva r : getRepoReservas().getTodos())
        {
            if (r.getClaseDeSurf().getIdClase() == idClase)
            {
                Alumno alumno = r.getAlumno();
                arrAlumnos.add(alumno);
            }
        }

        return arrAlumnos;
    }
}