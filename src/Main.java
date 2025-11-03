import Clases.*;
import Enumeradores.MetodoPago;
import Enumeradores.NivelDeSurf;
import Enumeradores.NombreEquipo;
import Enumeradores.TipoClase;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoInvalidoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.CupoLlenoException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.FechaInvalidaException;
import ExcepcionesPersonalizadas.ExcepcionesClaseDeSurf.PagoPendienteException;

static EscuelaDeSurf escuela = null;
static Instructor instructor = null;
static Cliente cliente = null;
static Alumno alumno = null;
static ClaseDeSurf clase = null;
static Alquiler alquiler = null;

static Scanner scanner = new Scanner(System.in);

public static void caso1() //crear escuela de surf
{
    if (escuela == null)
    {
        escuela = new EscuelaDeSurf();
        System.out.println("Escuela de Surf creada correctamente...");
    }
    else
    {
        System.out.println("️⚠️: La escuela ya ha sido creada anteriormente.");
    }
}

public static void caso2() //agregar alumno
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
            alumno = new Alumno(47559633, "Ciro", "Marzoni", 18, 22384625, NivelDeSurf.PRINCIPIANTE, 0);
            String resultado = escuela.getRepoAlumnos().agregar(1, alumno) ? "Alumno creado y agregado al repositorio de alumnos correctamente." : "No se pudo agregar al alumno.";
            System.out.println(resultado);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("❌ Error de datos al crear el alumno: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado al procesar el alumno: " + e.getMessage());
        }
    }
}

public static void caso3() //agregar instructor
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
            instructor = new Instructor(47279633, "Balta", "Llorca", 18, 22329625, 2);
            String resultado = escuela.getRepoInstructores().agregar(1, instructor) ? "Instructor creado y agregado al repositorio de instructores correctamente." : "No se pudo agregar al instructor.";
            System.out.println(resultado);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("❌ Error de datos al crear el instructor: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado al procesar el instructor: " + e.getMessage());
        }

    }
}

public static void caso4() //agregar cliente
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

public static void caso5() //crear clase de surf
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

public static void caso6() //Agregar alquiler con varios equipos para un cliente
{
    alquiler = new Alquiler(cliente, LocalDate.of(2026, 10, 27));
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

public static void caso7() //buscar alumno por su id
{
    Alumno alumno = escuela.getRepoAlumnos().buscarPorId(1);
    if (alumno != null)
    {
        System.out.println("Alumno encontrado: " + alumno);
    }
    else
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 2)");
    }
}

public static void caso8() //metodo: reservar clase de alumno
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
        catch (IllegalStateException e)
        {
            System.out.println("⚠️ Error de estado: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado al reservar: " + e.getMessage());
        }
    }
}

public static void caso9() //mostrar reservas de un alumno
{
    if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno. Elija la (opción 3)");
    }

    //alumno.mostrarReservas();
}

public static void caso10() //mostrar alumnos inscriptos en una clase
{
    if (clase == null)
    {
        System.out.println("⚠️: Primero debe crear una clase. Elija la (opción 5)");
    }

    //clase.mostrarAlumnosInscriptos();

}

public static void caso11() //cancelar una reserva
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

public static void caso12() //grabar repositorios a json
{
    JsonUtiles.gabrarRepositorioEnJson(escuela.getRepoAlumnos(),
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

public static void caso13() //leer y cargar el archivo json de repositorios
{

}

public static void caso14() //mostrar todos los repositorios
{
    if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else
    {
        escuela.mostrarEscuelaDeSurf();
    }
}

void main()
{
    int opcion;

    do
    {
        System.out.println("\nMENU ESCUELA DE SURF\n");
        System.out.println("1. Crear la Escuela de Surf.");
        System.out.println("2. Agregar Alumno.");
        System.out.println("3. Agregar Instructor.");
        System.out.println("4. Agregar Cliente.");
        System.out.println("5. Agregar Clase de Surf.");
        System.out.println("6. Agregar alquiler con varios equipos para un cliente.");
        System.out.println("7. Buscar alumno por su id.");
        System.out.println("8. Método: Reservar clase de Alumno.");
        System.out.println("9. Mostrar reservas de un alumno");
        System.out.println("10. Mostrar alumnos inscriptos en una clase");
        System.out.println("11. Cancelar una reserva");
        System.out.println("12. Grabar repositorios a json");
        System.out.println("13. Leer el archivo json de repositorios");
        System.out.println("14. Mostrar todos los repositorios.");

        System.out.println("999. Salir.");

        System.out.print("Ingrese una de las opciones: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion)
        {
            case 1 -> caso1();
            case 2 -> caso2();
            case 3 -> caso3();
            case 4 -> caso4();
            case 5 -> caso5();
            case 6 -> caso6();
            case 7 -> caso7();
            case 8 -> caso8();
            case 9 -> caso9();
            case 10 -> caso10();
            case 11 -> caso11();
            case 12 -> caso12();
            case 13 -> caso13();
            case 14 -> caso14();
            case 999 -> System.out.println("\nSaliendo del programa...");
            default -> System.out.println("\nIngrese una opción valida...");
        }
    } while (opcion != 999);
}

