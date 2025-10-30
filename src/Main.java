import Clases.*;
import Enumeradores.NivelDeSurf;
import Enumeradores.TipoClase;

static EscuelaDeSurf escuela = null;
static Instructor instructor = null;
static Cliente cliente = null;
static Alumno alumno = null;
static ClaseDeSurf clase = null;

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
        //Hardcodeado
        alumno = new Alumno(47559633, "Ciro", "Marzoni", 18, 22384625, NivelDeSurf.PRINCIPIANTE, 0);
        escuela.getRepoAlumnos().agregar(1, alumno);
        System.out.println("Alumno creado y agregado al repositorio de alumnos correctamente...");
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
        //Hardcodeado
        instructor = new Instructor(47279633, "Balta", "Llorca", 18, 22329625, 2);
        escuela.getRepoInstructores().agregar(1, instructor);
        System.out.println("Instructor creado y agregado al repositorio de instructores correctamente...");
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
        //Hardcodeado
        cliente = new Cliente(48239633, "Juan", "Ferreyra", 35, 223223854);
        escuela.getRepoClientes().agregar(1, cliente);
        System.out.println("Cliente creado y agregado al repositorio de clientes correctamente...");
    }
}

public static void caso5() //crear clase de surf
{
    if (instructor == null)
    {
        System.out.println("⚠️: Primero debe crear un instructor.");
    }
    else if (escuela == null)
    {
        System.out.println("⚠️: Primero debe crear la escuela. Elija la (opción 1).");
    }
    else
    {
        clase = new ClaseDeSurf(instructor, TipoClase.PARTICULAR, LocalDateTime.of(2025, 10, 27, 16, 30), 1);
        escuela.getRepoClases().agregar(1, clase);
        System.out.println("Clase de surf creada y agregada al repositorio de clases correctamente...");
    }
}

public static void caso6() //mostrar todos los repositorios
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

public static void caso7() //buscar alumno por su id
{
    Alumno alumno = escuela.getRepoAlumnos().buscarPorId(1);
    if (alumno != null)
    {
        System.out.println("Alumno encontrado: " + alumno);
    }
    else
    {
        System.out.println("⚠️: No existe el alumno");
    }
}

public static void caso8() //metodo: reservar clase de alumno
{
    if (alumno == null)
    {
        System.out.println("⚠️: Primero debe crear un alumno.");
    }
    else
    {
        alumno.reservar(clase);
    }
}

public static void caso9()
{
    if (alumno == null){
        System.out.println("⚠️: Primero debe crear un alumno.");
        return;
    }

}

public static void caso10()
{
    if(clase == null)
    {
        System.out.println("⚠️: Primero debe crear una clase.");
        return;
    }

    clase.mostrarAlumnosInscriptos();

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
        System.out.println("6. Mostrar todos los repositorios.");
        System.out.println("7. Buscar alumno por su id.");
        System.out.println("8. Método: Reservar clase de Alumno.");
        System.out.println("9. ");
        System.out.println("10. ");
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
            case 999 -> System.out.println("\nSaliendo del programa...");
            default -> System.out.println("\nIngrese una opción valida...");
        }
    } while (opcion != 999);
}

