package Clases;

public class EscuelaDeSurf
{
    private final Repositorio<Instructor> repoInstructores;
    private final Repositorio<ClaseDeSurf> repoClases;
    private final Repositorio<Alumno> repoAlumnos;
    private final Repositorio<Cliente> repoClientes;
    private final Repositorio<Reserva> repoReservas;
    private final Repositorio<Equipo> repoEquipos;
    private final Repositorio<Alquiler> repoAlquileres;

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

    @Override
    public String toString()
    {
        return "EscuelaDeSurf{" +
                "repoInstructores=" + repoInstructores +
                ", repoClases=" + repoClases +
                ", repoAlumnos=" + repoAlumnos +
                ", repoClientes=" + repoClientes +
                ", repoReservas=" + repoReservas +
                ", repoEquipos=" + repoEquipos +
                ", repoAlquileres=" + repoAlquileres +
                '}';
    }

    public void mostrarEscuelaDeSurf()
    {
        System.out.println("--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE ALUMNOS: " + repoAlumnos);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE INSTRUCTORES: " + repoInstructores);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLIENTES: " + repoClientes);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLASES: " + repoClases);
        System.out.println("\n--------------------------------------------------");
        System.out.println("REPOSITORIO DE RESERVAS: " + repoReservas);
        System.out.println("\n--------------------------------------------------");
        System.out.println("REPOSITORIO DE EQUIPOS: " + repoEquipos);
        System.out.println("\n--------------------------------------------------");
        System.out.println("REPOSITORIO DE ALQUILERES: " + repoAlquileres);
        System.out.println("\n--------------------------------------------------");
    }
}
