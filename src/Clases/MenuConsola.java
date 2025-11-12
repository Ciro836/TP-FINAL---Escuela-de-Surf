package Clases;

import Enumeradores.NivelDeSurf;
import Enumeradores.NombreEquipo;

import java.util.InputMismatchException;
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
            System.out.println("2. Agregar Equipo.");
            System.out.println("3. Agregar Instructor.");
            System.out.println("4. Agregar Cliente.");
            System.out.println("5. Agregar Clase de Surf.");
            System.out.println("6. Agregar alquiler con varios equipos para un cliente.");
            System.out.println("7. Buscar alumno por su id.");
            System.out.println("8. Método: Reservar clase de Alumno.");
            System.out.println("9. Mostrar reservas de un alumno.");
            System.out.println("10. Mostrar alumnos inscriptos en una clase.");
            System.out.println("11. Cancelar una reserva.");
            System.out.println("12. Grabar repositorios a json.");
            System.out.println("13. Leer el archivo json de repositorios.");
            System.out.println("14. Mostrar todos los repositorios.");
            System.out.println("15. Chequear morosidad de alumno y cliente.");

            System.out.println("999. Salir.");

            try
            {
                System.out.print("Ingrese una de las opciones: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion)
                {
                    case 1 -> agregarAlumno();
                    case 2 -> agregarEquipo();
                    //case 3 -> agregarInstructor();
                    //case 4 -> agregarCliente();
                    //case 5 -> crearClaseDeSurf();
                    //case 6 -> agregarAlquiler();
                    //case 7 -> buscarAlumnoPorId();
                    //case 8 -> alumnoReservarClase();
                    //case 9 -> mostrarReservasAlumno();
                    //case 10 -> mostrarAlumnosInscriptosEnClase();
                    //case 11 -> cancelarReserva();
                    //case 12 -> grabarRepositoriosAjson();
                    //case 13 -> leerYmostrarJsonDeRepositorios();
                    case 14 -> mostrarTodosLosRepositorios();
                    //case 15 -> chequearMorosidad();
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
        char seguir = 's';
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

            try
            {
                System.out.print("Desea seguir cargando alumnos? (s/n): ");
                char entrada = scanner.next().toLowerCase().charAt(0);
                if (entrada == 's' || entrada == 'n')
                {
                    seguir = entrada;
                }
                else
                {
                    throw new IllegalArgumentException("Error: debes ingresar alguna de esta letras: (s/n).");
                }
                scanner.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError: debes ingresar(s/n).");
                scanner.nextLine();
            }

        } while (seguir == 's');
    }

    public void agregarEquipo()
    {
        char seguir = 's';
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

            try
            {
                System.out.print("Desea seguir cargando equipos? (s/n): ");
                char entrada = scanner.next().toLowerCase().charAt(0);
                if (entrada == 's' || entrada == 'n')
                {
                    seguir = entrada;
                }
                else
                {
                    throw new IllegalArgumentException("Error: debes ingresar alguna de esta letras: (s/n).");
                }
                scanner.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError: debes ingresar(s/n).");
                scanner.nextLine();
            }
        } while (seguir == 's');
    }

}
