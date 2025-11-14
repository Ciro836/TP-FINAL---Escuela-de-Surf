import Clases.EscuelaDeSurf;
import Clases.MenuConsola;

void main()
{
    EscuelaDeSurf escuela = new EscuelaDeSurf();

    MenuConsola menu = new MenuConsola(escuela);

    menu.ejecutarMenu();
}

