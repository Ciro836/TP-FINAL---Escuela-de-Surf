import Clases.EscuelaDeSurf;
import Clases.MenuConsola;

/*
public static void grabarRepositoriosAjson()
{
    JsonUtiles.grabarRepositorioEnJson(escuela.getRepoAlumnos(),
            escuela.getRepoInstructores(),
            escuela.getRepoClases(),
            escuela.getRepoClientes(),
            escuela.getRepoReservas(),
            escuela.getRepoEquipos(),
            escuela.getRepoAlquileres(),
            escuela.getRepoPagos(),
            "escuelaDeSurf.json");

    System.out.println("Repositorios grabados en escuelaDeSurf.json");
}

public static void leerYmostrarJsonDeRepositorios()
{
    JSONTokener tokener = JsonUtiles.leerUnJson("escuelaDeSurf.json");

    JSONObject objetoJson = new JSONObject(tokener);

    JSONArray repoPagos = objetoJson.getJSONArray("repoPagos");
    System.out.println("REPOSITORIO PAGOS: " + repoPagos);

    JSONArray repoClases = objetoJson.getJSONArray("repoClases");
    System.out.println("REPOSITORIO CLASES: " + repoClases);

    JSONArray repoAlumnos = objetoJson.getJSONArray("repoAlumnos");
    System.out.println("REPOSITORIO ALUMNOS: " + repoAlumnos);

    JSONArray repoReservas = objetoJson.getJSONArray("repoReservas");
    System.out.println("REPOSITORIO RESERVAS: " + repoReservas);

    JSONArray repoAlquileres = objetoJson.getJSONArray("repoAlquileres");
    System.out.println("REPOSITORIO ALQUILERES: " + repoAlquileres);

    JSONArray repoClientes = objetoJson.getJSONArray("repoClientes");
    System.out.println("REPOSITORIO CLIENTES: " + repoClientes);

    JSONArray repoEquipos = objetoJson.getJSONArray("repoEquipos");
    System.out.println("REPOSITORIO EQUIPOS: " + repoEquipos);

    JSONArray repoInstructores = objetoJson.getJSONArray("repoInstructores");
    System.out.println("REPOSITORIO INSTRUCTORES: " + repoInstructores);
}
*/

void main()
{
    EscuelaDeSurf escuela = new EscuelaDeSurf();

    MenuConsola menu = new MenuConsola(escuela);

    menu.ejecutarMenu();
}

