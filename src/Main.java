import Clases.EscuelaDeSurf;
import Clases.MenuConsola;

/*
public static void agregarCliente()
{
    if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else
    {
        try
        {
            //Hardcodeado
            cliente = new Cliente(48239633, "Juan", "Ferreyra", 35, 223223854);
            String resultado = escuela.getRepoClientes().agregar(1, cliente) ? "Cliente creado y agregado al repositorio de clientes correctamente." : "No se a podido agregar al cliente.";
            System.out.println(resultado);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("❌ Error de datos al crear el cliente: " + e.getMessage());
        }
        catch (Exception e) // 👈 Nuevo: Manejo de error general
        {
            System.out.println("⚠️ Error inesperado al procesar el cliente: " + e.getMessage());
        }
    }
}

public static void crearClaseDeSurf()
{
    if (instructor == null)
    {
        System.out.println("⚠️: Primero debe crear un instructor. Elija la (opción 3).");
    }
    else if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else
    {
        try
        {
            clase = new ClaseDeSurf(instructor, TipoClase.PARTICULAR, LocalDateTime.of(2026, 10, 27, 16, 30), 1);
            String resultado = escuela.getRepoClases().agregar(1, clase) ? "Clase de surf creada y agregada al repositorio de clases correctamente." : "No se a podido crear la clase.";
            System.out.println(resultado);
        }
        catch (FechaInvalidaException e)
        {
            System.out.println("❌ Error de fecha: " + e.getMessage());
        }
        catch (CupoInvalidoException e)
        {
            System.out.println("❌ Error de cupo: " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("❌ Error de datos: " + e.getMessage());
        }
        catch (Exception e) // 👈 Nuevo: Captura cualquier otra excepción no manejada
        {
            System.out.println("⚠️ Error inesperado al crear la clase: " + e.getMessage());
        }
    }
}

public static void agregarAlquiler()
{
    alquiler = new Alquiler(LocalDate.of(2026, 10, 27));
    Equipo equipo = new Equipo(NombreEquipo.TABLA_DE_SURF);
    Equipo equipo2 = new Equipo(NombreEquipo.TRAJE_DE_NEOPRENE);
    alquiler.agregarEquipo(equipo);
    alquiler.agregarEquipo(equipo2);

    cliente.agregarAlquiler(alquiler);

    Pago pagoAlquiler = new Pago();
    pagoAlquiler.setMonto(alquiler.getMontoTotal());
    cliente.pagar(pagoAlquiler, MetodoPago.TRANSFERENCIA);


    escuela.getRepoEquipos().agregar(1, equipo);
    escuela.getRepoEquipos().agregar(2, equipo2);
    escuela.getRepoPagos().agregar(1, pagoAlquiler);
    escuela.getRepoAlquileres().agregar(1, alquiler);

    System.out.println("Alquiler con su lista de equipos y pagos agregado correctamente.");
}

public static void alumnoReservarClase()
{
    if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 2)");
    }
    else
    {
        try
        {
            alumno.reservar(clase, MetodoPago.EFECTIVO);

            for (Reserva reserva : alumno.getReservas())
            {
                escuela.getRepoReservas().agregar(reserva.getIdReserva(), reserva);
            }

            for (Pago pago : alumno.getPagos())
            {
                escuela.getRepoPagos().agregar(pago.getIdPago(), pago);
            }

            System.out.println("Se reservó correctamente la clase.");
        }
        catch (CupoLlenoException e) // la clase no tiene más espacio
        {
            System.out.println("❌ Error de reserva: " + e.getMessage());
        }
        catch (PagoPendienteException e) // el alumno es moroso
        {
            System.out.println("❌ Error de reserva: " + e.getMessage());
        }
        catch (IllegalStateException | IllegalArgumentException e)
        {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado al reservar: " + e.getMessage());
        }
    }
}

public static void mostrarReservasAlumno()
{
    if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 2)");
    }

    if (alumno.getReservas().isEmpty())
    {
        System.out.println("⚠️: Primero debe agregar una reserva. Elija la (opción 8)");
    }

    alumno.mostrarReservas();
}

public static void mostrarAlumnosInscriptosEnClase()
{
    if (clase == null)
    {
        System.out.println("⚠️: Primero debe crear una clase. Elija la (opción 5)");
    }

    clase.mostrarAlumnosInscriptos();
}

public static void cancelarReserva()
{
    if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 2).");
    }

    if (alumno.getReservas().isEmpty())
    {
        System.out.println("⚠️: El alumno no tiene reservas realizadas. Elija la (opción 8).");
    }

    System.out.println("Ingrese el ID de la reserva a cancelar: ");
    int idReserva = scanner.nextInt();
    scanner.nextLine();

    boolean flag = false;

    for (Reserva reserva : alumno.getReservas())
    {
        if (reserva.getIdReserva() == idReserva)
        {
            alumno.cancelarReserva(reserva);
            escuela.getRepoReservas().eliminar(idReserva);
            System.out.println("Reserva cancelada con éxito");
            flag = true;
            break;
        }
    }
    if (!flag)
    {
        System.out.println("No se econtro una reserva con ese ID.");
    }
}

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

public static void mostrarRepositorios()
{
    if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else
    {
        escuela.mostrarTodosLosRepositorios();
    }
}

public static void chequearMorosidad()
{
    if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 2).");
    }
    else if (cliente == null)
    {
        System.out.println("⚠️: Primero debe crear un cliente. Elija la (opción 4).");
    }
    else
    {
        String resultadoAlumno = alumno.esMoroso() ? "El Alumno es un deudor!!!" : "El alumno tiene sus pagos en tiempo y forma.";
        System.out.println(resultadoAlumno);

        String resultadoCliente = cliente.esMoroso() ? "El Cliente es un deudor!!!" : "El cliente tiene sus pagos en tiempo y forma.";
        System.out.println(resultadoCliente);
    }
}

*/

void main()
{
    EscuelaDeSurf escuela = new EscuelaDeSurf();

    MenuConsola menu = new MenuConsola(escuela);

    menu.ejecutarMenu();
}

