package Clases;

public class EscuelaDeSurf
{
    private final Repositorio<Instructor> repoInstructores;
    private final Repositorio<ClaseDeSurf> repoClases;
    private final Repositorio<Alumno> repoAlumnos;
    private final Repositorio<Cliente> repoClientes;

    /// CONSTRUCTOR

    public EscuelaDeSurf()
    {
        this.repoInstructores = new Repositorio<>();
        this.repoClases = new Repositorio<>();
        this.repoAlumnos = new  Repositorio<>();
        this.repoClientes = new Repositorio<>();
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
}
