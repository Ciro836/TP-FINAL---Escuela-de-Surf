package Clases;

import Enumeradores.MetodoPago;
import Enumeradores.NivelDeSurf;
import Enumeradores.NombreEquipo;
import Enumeradores.TipoClase;
import ExcepcionesPersonalizadas.CupoInvalidoException;
import ExcepcionesPersonalizadas.FechaInvalidaException;
import ExcepcionesPersonalizadas.IdNoEncontradoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuConsola //Clase para encargarse de la gestión de la interfaz de usuario
{
    private final EscuelaDeSurf escuela; //La lógica de negocio
    private final Scanner scanner;

    public MenuConsola(EscuelaDeSurf escuela)
    {
        this.escuela = escuela;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutarMenu()
    {
        int opcion = 0;

        do
        {
            System.out.println("\nMENU ESCUELA DE SURF\n");
            System.out.println("1. Agregar Alumno.");
            System.out.println("2. Agregar Instructor.");
            System.out.println("3. Agregar Clase de Surf.");
            System.out.println("4. Agregar Reserva.");
            System.out.println("5. Agregar Cliente.");
            System.out.println("6. Agregar Equipo.");
            System.out.println("7. Agregar Alquiler.");
            System.out.println("8. Buscar alumno por su id.");
            System.out.println("9. Mostrar reservas de un alumno.");
            System.out.println("10. Mostrar alumnos inscriptos en una clase.");
            System.out.println("11. Cancelar una reserva.");
            System.out.println("12. Pagar una reserva de clase.");
            System.out.println("13. Pagar un alquiler de equipo.");
            System.out.println("14. Chequear morosidad de alumno.");
            System.out.println("15. Chequear morosidad de cliente.");
            System.out.println("16. Grabar repositorios a json.");
            System.out.println("17. Leer e importar el archivo json de repositorios.");
            System.out.println("18. Mostrar todos los repositorios.");

            System.out.println("999. Salir.");

            try
            {
                System.out.print("Ingrese una de las opciones: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion)
                {
                    case 1 -> agregarAlumno();
                    case 2 -> agregarInstructor();
                    case 3 -> agregarClaseDeSurf();
                    case 4 -> agregarReserva();
                    //case 5 -> agregarCliente();
                    case 6 -> agregarEquipo();
                    //case 7 -> agregarAlquiler();
                    case 8 -> buscarAlumnoPorId();
                    case 9 -> mostrarReservasAlumno();
                    case 10 -> mostrarAlumnosInscriptosEnClase();
                    //case 11 -> cancelarReserva();
                    case 12 -> pagarUnaReserva();
                    case 13 -> pagarUnAlquiler();
                    case 14 -> chequearMorosidadAlumno();
                    case 15 -> chequearMorosidadCliente();
                    case 16 -> grabarRepositoriosAjson();
                    case 17 -> leerJsonDeRepositorios();
                    case 18 -> mostrarTodosLosRepositorios();
                    case 999 -> System.out.println("\nSaliendo del programa...");
                    default -> System.out.println("\nIngrese una opción valida...");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError: debes ingresar un número.");
                scanner.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("\nError inesperado: " + e.getMessage());
            }
        } while (opcion != 999);
    }

    private boolean deseaContinuar(String mensaje)
    {
        while (true) // Bucle infinito hasta que se ingrese 's' o 'n'
        {
            try
            {
                System.out.print(mensaje + " (s/n): ");
                char entrada = scanner.next().toLowerCase().charAt(0);
                scanner.nextLine();

                if (entrada == 's')
                {
                    return true;
                }
                if (entrada == 'n')
                {
                    return false;
                }

                throw new IllegalArgumentException("Error: debes ingresar 's' o 'n'.");
            }
            catch (Exception e)
            {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            }
        }
    }

    public void mostrarTodosLosRepositorios()
    {
        System.out.println("--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE ALUMNOS: " + escuela.getRepoAlumnos());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE INSTRUCTORES: " + escuela.getRepoInstructores());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLIENTES: " + escuela.getRepoClientes());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLASES: " + escuela.getRepoClases());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE RESERVAS: " + escuela.getRepoReservas());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE EQUIPOS: " + escuela.getRepoEquipos());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE ALQUILERES: " + escuela.getRepoAlquileres());
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE PAGOS: " + escuela.getRepoPagos());
        System.out.println("\n--------------------------------------------------");
    }

    public void agregarAlumno()
    {
        do
        {
            try
            {
                System.out.println("CARGA DE DATOS DE ALUMNOS\n");

                System.out.print("Ingrese el numero de DNI: ");
                String dni = scanner.nextLine().trim();

                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = scanner.nextLine().trim();

                System.out.print("Ingrese el apellido del alumno: ");
                String apellido = scanner.nextLine().trim();

                System.out.print("Ingrese la edad del alumno: ");
                int edad = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el numero de telefono: ");
                String telefono = scanner.nextLine().trim();

                System.out.print("Ingrese el nivel de surf(Principiante/Intermedio/Avanzado): ");
                String nivelTexto = scanner.nextLine().trim().toUpperCase();
                NivelDeSurf nivel;
                try
                {
                    nivel = NivelDeSurf.valueOf(nivelTexto);
                }
                catch (IllegalArgumentException ex)
                {
                    throw new IllegalArgumentException("Nivel de surf inválido. Use: Principiante, Intermedio o Avanzado.");
                }

                System.out.print("Ingrese la cant de clases tomadas por el alumno: ");
                int cantDeClases = scanner.nextInt();
                scanner.nextLine();

                Alumno alumno = new Alumno(dni, nombre, apellido, edad, telefono, nivel, cantDeClases);
                escuela.registrarNuevoAlumno(alumno);
                System.out.println("Alumno agregado correctamente.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("❌ Error de datos al crear el alumno: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("⚠️ Error inesperado al procesar el alumno: " + e.getMessage());
            }

        } while (deseaContinuar("Desea seguir cargando alumnos?"));
    }

    public void agregarEquipo()
    {
        do
        {
            try
            {
                System.out.println("CARGA DE DATOS DE EQUIPOS\n");

                NombreEquipo[] tiposDeEquipo = NombreEquipo.values();

                System.out.println("Seleccione el tipo de equipo:");
                for (int i = 0; i < tiposDeEquipo.length; i++)
                {
                    System.out.println((i) + ". " + tiposDeEquipo[i].getNombreEquipo());
                }
                System.out.print("Ingrese el número de la opción: ");

                int seleccion = scanner.nextInt();
                scanner.nextLine();

                if (seleccion < 0 || seleccion > tiposDeEquipo.length)
                {
                    throw new IllegalArgumentException("Selección inválida. Ingrese un número entre 0 y " + tiposDeEquipo.length + ".");
                }

                NombreEquipo nombreEnum = tiposDeEquipo[seleccion];

                Equipo equipo = new Equipo(nombreEnum);
                escuela.registrarNuevoEquipo(equipo);
                System.out.println("Equipo agregado correctamente.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("❌ Error de datos: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }

        } while (deseaContinuar("Desea seguir cargando equipos?"));
    }

    public void agregarInstructor()
    {
        do
        {
            try
            {
                System.out.println("CARGA DE DATOS DE INSTRUCTORES\n");

                System.out.print("Ingrese el numero de DNI: ");
                String dni = scanner.nextLine().trim();

                System.out.print("Ingrese el nombre del instructor: ");
                String nombre = scanner.nextLine().trim();

                System.out.print("Ingrese el apellido del instructor: ");
                String apellido = scanner.nextLine().trim();

                System.out.print("Ingrese la edad del instructor: ");
                int edad = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el numero de telefono: ");
                String telefono = scanner.nextLine().trim();

                System.out.print("Ingrese los años de experiencia del instructor: ");
                int aniosExp = scanner.nextInt();
                scanner.nextLine();

                Instructor instructor = new Instructor(dni, nombre, apellido, edad, telefono, aniosExp);
                escuela.registrarNuevoInstructor(instructor);
                System.out.println("Alumno agregado correctamente.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("❌ Error de datos: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }

        } while (deseaContinuar("Desea seguir cargando instructores?"));
    }

    public void agregarClaseDeSurf()
    {
        do
        {
            try
            {
                System.out.println("CARGA DE DATOS DE CLASES DE SURF\n");

                //INSTRUCTOR
                System.out.print("Ingrese el id del instructor que dictará la clase: ");
                int idInstructor = scanner.nextInt();
                scanner.nextLine();
                Instructor instructor = escuela.buscarInstructorPorId(idInstructor);

                //TIPO DE CLASE
                System.out.println("Ingrese el tipo de clase: ");
                System.out.println("1. Clase grupal");
                System.out.println("2. Clase particular");
                int respuesta = scanner.nextInt();
                scanner.nextLine();

                TipoClase tipoClase = null;
                switch (respuesta)
                {
                    case 1 -> tipoClase = TipoClase.GRUPAL;
                    case 2 -> tipoClase = TipoClase.PARTICULAR;
                    default -> throw new IllegalArgumentException("Opción de tipo de clase no válida.");
                }

                //FECHA Y HORA
                System.out.print("Ingrese la fecha de la clase (formato YYYY-MM-DD): ");
                String fechaStr = scanner.nextLine().trim();
                System.out.print("Ingrese la hora de la clase (formato HH:MM, 24hs): ");
                String horaStr = scanner.nextLine().trim();

                // Combinamos la fecha y la hora
                String fechaHoraStr = fechaStr + " " + horaStr;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

                //CUPO MÁXIMO
                System.out.print("Ingrese el cupo máximo de alumnos: ");
                int cupoMax = scanner.nextInt();
                scanner.nextLine();

                //CREACION DE CLASE y ademas se agrega al repo de clases.
                ClaseDeSurf clase = new ClaseDeSurf(instructor, tipoClase, fechaHora, cupoMax);
                escuela.registrarNuevaClase(clase);
                System.out.println("Clase agregada correctamente.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
                scanner.nextLine();
            }
            catch (DateTimeParseException e)
            {
                System.out.println("❌ Error en el formato de fecha u hora. Use YYYY-MM-DD y HH:MM.");
            }
            catch (IdNoEncontradoException e)
            {
                System.out.println("❌ Error: " + e.getMessage()); //
            }
            catch (FechaInvalidaException | CupoInvalidoException e)
            {
                System.out.println("❌ Error al crear la clase: " + e.getMessage()); //
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("❌ Error de datos: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }

        } while (deseaContinuar("Desea seguir cargando clases de surf?"));
    }

    public void agregarReserva()
    {
        do {
            try {
                System.out.println("CARGA DE DATOS A UNA RESERVA\n");

                //Elijo si es una reserva para un alumno nuevo o ya existe
                System.out.println("1) Seleccionar un alumno ya existente");
                System.out.println("2) Registrar un nuevo alumno");
                System.out.println("Ingrese la opción: ");
                int opcionAlumno = scanner.nextInt();
                scanner.nextLine();

                Alumno alumno = null;

                //listar alumno ya existente para que elija
                if (opcionAlumno == 1) {
                    if (escuela.getRepoAlumnos().getDatos().isEmpty()) {
                        System.out.println("No hay alumnos previamente cargados.");
                        opcionAlumno = 2; //fuerzo la creación de uno nuevo
                    } else {
                        System.out.println("Listado de alumnos existente: ");
                        escuela.getRepoAlumnos().getTodos();

                        System.out.println("Ingrese el ID del alumno: ");
                        int idAlumno = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            alumno = escuela.buscarAlumnoPorId(idAlumno);
                        } catch (IdNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }
                if (opcionAlumno == 2) {
                    scanner.nextLine();

                    agregarAlumno();
                    //paso la coleccione de valores que devuelve getTodos a un arrayList, en este caso alumnos para ponerles un indica y obtener el ultimo
                    List<Alumno> alumnos = new ArrayList<>(escuela.getRepoAlumnos().getTodos());

                    if (alumnos.isEmpty()) {
                        System.out.println("ERROR: No se pudo crear correctamente el alumno.");
                        return;
                    }

                    alumno = alumnos.getLast(); //obtengo el alumno cargado mas reciente
                    System.out.println("Alumno seleccionado correctamente.");

                }

                //Elijo si selec. una clase existente o una nueva

                System.out.println("1) Seleccionar una clase ya existente");
                System.out.println("2) Registrar una nueva clase");
                System.out.println("Ingrese la opción: ");
                int opcionClase = scanner.nextInt();
                scanner.nextLine();

                ClaseDeSurf clase = null;

                if (opcionClase == 1) {
                    if (escuela.getRepoClases().getDatos().isEmpty()) {
                        System.out.println("No hay clases previamente cargadas.");
                        opcionClase = 2; //fuerzo la creación de uno nuevo
                    } else {
                        System.out.println("Listado de clases existentes: ");
                        escuela.getRepoClases().getTodos();

                        System.out.println("Ingrese el ID de la clase: ");
                        int idClase = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            clase = escuela.buscarClasePorId(idClase);
                        } catch (IdNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }

                if (opcionClase == 2) {
                    scanner.nextLine();

                    agregarClaseDeSurf();
                    //paso la coleccione de valores que devuelve getTodos a un arrayList, en este caso clase de surf para ponerles un indica y obtener el ultimo
                    List<ClaseDeSurf> clases = new ArrayList<>(escuela.getRepoClases().getTodos());

                    if (clases.isEmpty()) {
                        System.out.println("ERROR: No se pudo crear correctamente la clase.");
                        return;
                    }

                    clase = clases.getLast(); //obtengo el alumno cargado mas reciente
                    System.out.println("Clase seleccionada correctamente.");
                }

                try {
                    Reserva reserva = new Reserva(alumno, clase);
                    escuela.registrarNuevaReserva(reserva);
                    System.out.println("Reserva registrada correctamente.");
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Error al crear la reserva: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("❌ Error inesperado al registrar la reserva: " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }
        }while(deseaContinuar("Desea seguir cargando reservas?"));
    }

    public void agregarCliente()
    {
        do {
            try {
                System.out.println("CARGA DE DATOS DE CLIENTES\n");

                System.out.print("Ingrese el numero de DNI: ");
                String dni = scanner.nextLine().trim();

                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = scanner.nextLine().trim();

                System.out.print("Ingrese el apellido del alumno: ");
                String apellido = scanner.nextLine().trim();

                System.out.print("Ingrese la edad del alumno: ");
                int edad = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el numero de telefono: ");
                String telefono = scanner.nextLine().trim();

                Cliente cliente = new  Cliente(dni, nombre, apellido, edad, telefono);
                escuela.registrarNuevoCliente(cliente);
                }catch (InputMismatchException e)
                {
                    System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
                    scanner.nextLine();
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("❌ Error de datos al crear el alumno: " + e.getMessage());
                }
            catch (Exception e)
            {
               System.out.println("⚠️ Error inesperado al procesar el alumno: " + e.getMessage());
            }

        } while (deseaContinuar("Desea seguir cargando alumnos?"));
    }

    public void buscarAlumnoPorId()
    {
        //Pedimos que el usuario ingrese el id
        int id = 0;
        try
        {
            System.out.print("Ingresa el id de el alumno a buscar: ");
            id = scanner.nextInt();
            scanner.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println("\nError: ingresa un valor correcto. " + e.getMessage());
            scanner.nextLine();
        }
        catch (Exception e)
        {
            System.out.println("\nError inesperado: " + e.getMessage());
        }

        //Buscamos el alumno con ese id
        try
        {
            Alumno alumnito = escuela.buscarAlumnoPorId(id);
            System.out.println("✅ Alumno encontrado!");
            System.out.println(alumnito);
        }
        catch (IdNoEncontradoException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarReservasAlumno()
    {
        //le pedimos al usuario el id del alumno para ver sus reservas
        int id = 0;
        try
        {
            System.out.print("Ingresa el id del alumno para ver sus reservas: ");
            id = scanner.nextInt();
            scanner.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println("❌ Error: debes ingresar un número.");
            scanner.nextLine();
            return;
        }

        //mostramos las reservas del alumno
        try
        {
            List<Reserva> reservas = escuela.buscarReservasPorAlumnoId(id);//este metodo lanza la excepcion de idNoEncontrado

            if (reservas.isEmpty())
            {
                System.out.println("El alumno con ID " + id + " existe, pero no tiene reservas activas.");
            }
            else
            {
                System.out.println("Mostrando " + reservas.size() + " reserva(s) del alumno ID " + id + ":");
                for (Reserva r : reservas)
                {
                    System.out.println(r.mostrarReservaMejorada());
                }
            }
        }
        catch (IdNoEncontradoException e)
        {
            System.out.println("❌ Error: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }
    }

    private MetodoPago seleccionarMetodoPago() throws IllegalArgumentException, InputMismatchException
    {
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. " + MetodoPago.EFECTIVO);
        System.out.println("2. " + MetodoPago.TARJETA);
        System.out.println("3. " + MetodoPago.TRANSFERENCIA);
        System.out.print("Ingrese una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        return switch (opcion)
        {
            case 1 -> MetodoPago.EFECTIVO;
            case 2 -> MetodoPago.TARJETA;
            case 3 -> MetodoPago.TRANSFERENCIA;
            default -> throw new IllegalArgumentException("Opción de pago inválida.");
        };
    }

    public void pagarUnaReserva()
    {
        try
        {
            System.out.println("PAGO DE RESERVA DE CLASE\n");
            System.out.print("Ingrese el ID de la reserva que desea pagar: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();

            MetodoPago metodo = seleccionarMetodoPago();

            escuela.pagarReserva(idReserva, metodo);

            System.out.println("✅ Pago de reserva: " + idReserva + " registrado exitosamente!");
        }
        catch (InputMismatchException e)
        {
            System.out.println("❌ Error: debes ingresar un número.");
            scanner.nextLine();
        }
        catch (IdNoEncontradoException | IllegalStateException | IllegalArgumentException e)
        {
            System.out.println("❌ Error al procesar el pago: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }
    }

    public void pagarUnAlquiler()
    {
        try
        {
            System.out.println("PAGO DE ALQUILER DE EQUIPO\n");
            System.out.print("Ingrese el ID del alquiler que desea pagar: ");
            int idAlquiler = scanner.nextInt();
            scanner.nextLine();

            MetodoPago metodo = seleccionarMetodoPago();

            escuela.pagarAlquiler(idAlquiler, metodo);

            System.out.println("✅ Pago de alquiler: " + idAlquiler + " registrado exitosamente!");
        }
        catch (InputMismatchException e)
        {
            System.out.println("❌ Error: debes ingresar un número.");
            scanner.nextLine();
        }
        catch (IdNoEncontradoException | IllegalStateException | IllegalArgumentException e)
        {
            System.out.println("❌ Error al procesar el pago: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }
    }

    public void chequearMorosidadAlumno()
    {
        System.out.println("CHEQUEO DE MOROSIDAD\n");

        try
        {
            System.out.print("Ingrese el ID del ALUMNO a chequear: ");
            int idAlumno = scanner.nextInt();
            scanner.nextLine();

            boolean esMoroso = escuela.chequearMorosidadAlumno(idAlumno);
            if (esMoroso)
            {
                System.out.println("El alumno ID " + idAlumno + " TIENE pagos pendientes vencidos.");
            }
            else
            {
                System.out.println("El alumno ID " + idAlumno + " está al día con sus pagos.");
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("❌ Error: debe ingresar un número.");
            scanner.nextLine();
        }
        catch (IdNoEncontradoException e)
        {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void chequearMorosidadCliente()
    {
        System.out.println("CHEQUEO DE MOROSIDAD\n");

        try
        {
            System.out.print("Ingrese el ID del CLIENTE a chequear: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();

            boolean esMoroso = escuela.chequearMorosidadCliente(idCliente);
            if (esMoroso)
            {
                System.out.println("El cliente ID " + idCliente + " TIENE pagos pendientes vencidos.");
            }
            else
            {
                System.out.println("El cliente ID " + idCliente + " está al día con sus pagos.");
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("❌ Error: debe ingresar un número.");
            scanner.nextLine();
        }
        catch (IdNoEncontradoException e)
        {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void mostrarAlumnosInscriptosEnClase()
    {
        System.out.println("MOSTRAR ALUMNOS INSCRIPTOS\n");

        try
        {
            System.out.println("Ingrese el ID de la clase para mostrar sus alumnos: ");
            int idClase = scanner.nextInt();
            scanner.nextLine();

            List<Alumno> arrAlumnos = escuela.mostrarAlumnosDeUnaClase(idClase);

            if (arrAlumnos.isEmpty())
            {
                System.out.println("La clase ID " + idClase + " existe, pero no tiene alumnos inscriptos.");
            }
            else
            {
                for (Alumno alumno : arrAlumnos)
                {
                    System.out.println("  - " + alumno);
                }
            }
        }
        catch (IdNoEncontradoException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InputMismatchException e)
        {
            System.out.println("\nError: ingresa un valor correcto. " + e.getMessage());
            scanner.nextLine();
        }
        catch (Exception e)
        {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public void grabarRepositoriosAjson()
    {
        escuela.grabarRepositoriosAjson();
    }

    public void leerJsonDeRepositorios()
    {
        escuela.leerJsonDeRepositorios();
    }
}
