package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;
import Enumeradores.NivelDeSurf;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EscuelaDeSurf
{
    Scanner scanner = new Scanner(System.in);

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

                switch (opcion)
                {
                    case 1 -> agregarAlumno();
                    //case 2 -> agregarEquipo;
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
            }
            catch (Exception e)
            {
                System.out.println("\nError inesperado: " + e.getMessage());
            }
            finally
            {
                scanner.nextLine();
            }
        } while (opcion != 999);
    }

    public void mostrarTodosLosRepositorios()
    {
        System.out.println("--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE ALUMNOS: " + repoAlumnos);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE INSTRUCTORES: " + repoInstructores);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLIENTES: " + repoClientes);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE CLASES: " + repoClases);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE RESERVAS: " + repoReservas);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE EQUIPOS: " + repoEquipos);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE ALQUILERES: " + repoAlquileres);
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("REPOSITORIO DE PAGOS: " + repoPagos);
        System.out.println("\n--------------------------------------------------");
    }

    public void agregarAlumno()
    {
        try
        {
            char seguir;
            do
            {
                System.out.println("CARGA DE DATOS DE ALUMNOS\n");

                System.out.print("Ingrese el numero de DNI: ");
                int dni = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido del alumno: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese la edad del alumno: ");
                int edad = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el numero de telefono: ");
                int telefono = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el nivel de surf(Principiante/Intermedio/Avanzado): ");
                NivelDeSurf nivel = NivelDeSurf.valueOf(scanner.nextLine().toUpperCase());

                System.out.print("Ingrese la cant de clases tomadas por el alumno: ");
                int cantDeClases = scanner.nextInt();
                scanner.nextLine();

                Alumno alumno = new Alumno(dni, nombre, apellido, edad, telefono, nivel, cantDeClases);
                getRepoAlumnos().agregar(alumno);
                System.out.println("Alumno agregado correctamente.");

                System.out.print("Desea seguir cargando alumnos? (s/n): ");
                seguir = scanner.next().charAt(0);

            } while (seguir == 's');
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
    }

    public void pagar(Pago pago, MetodoPago metodo)
    {
        pago.setMetodoPago(metodo);
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago(EstadoPago.REALIZADO);
    }
}
