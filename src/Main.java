void main()
{
    public static void main (String[] args)
    {
        //probando...
        
        Repositorio <Alumno> repositorioAlumno = new Repositorio<>();

        repositorioAlumno.agregar(1, new Alumno("43851970", "Zoe","Arozamena ", 23 ,"2234496290",NivelDeSurf.PRINCIPIANTE, 5));

        repositorioAlumno.mostrarTodos();




    }



}
