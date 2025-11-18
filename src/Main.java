import Clases.EscuelaDeSurf;
import Clases.MenuConsola;

void main()
{
    EscuelaDeSurf escuela = new EscuelaDeSurf();

    System.out.println("\nIniciando sistema... Cargando base de datos...");
    escuela.leerJsonDeRepositorios();

    MenuConsola menu = new MenuConsola(escuela);

    menu.ejecutarMenu();
}

