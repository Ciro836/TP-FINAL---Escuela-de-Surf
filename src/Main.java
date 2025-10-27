void main()
{
    public static void main (String[] args)
    {
        //probando...
        
        Repositorio <Alumno> repositorioAlumno = new Repositorio<>();

        repositorioAlumno.agregar(1, new Alumno("43851970", "Zoe","Arozamena ", 23 ,"2234496290",NivelDeSurf.PRINCIPIANTE, 5));

        escuela.getRepoAlumnos().mostrarTodos();

        Alumno alumno = escuela.getRepoAlumnos().buscarPorId(1);
        if (alumno != null) {
            System.out.println("Alumno: " + alumno);
        }else{
            System.out.println("No existe alumno");
        }

        Instructor instructorNuevo = new Instructor("24734142", "Soledad", "Arias", 50, "22346161847", 15, 600000);

        ClaseDeSurf clase = new ClaseDeSurf(instructorNuevo, TipoClase.PARTICULAR, LocalDateTime.of(2025,10,27,16,30), 1);

        Pago pagoNuevo = new Pago();

        Reserva reservaNueva = new Reserva(alumnoNuevo, clase, pagoNuevo);

        escuela.getRepoAlumnos().mostrarTodos();

        escuela.getRepoClases().agregar(1, clase);
        escuela.getRepoClases().mostrarTodos();
        




    }



}
