package Utiles;

import Clases.*;
import Enumeradores.*;
import Interfaces.InterfazJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class JsonUtiles
{

    public static void grabarUnJson(JSONArray jsonArray, String archivo)
    {
        try
        {
            FileWriter file = new FileWriter(archivo);
            file.write(jsonArray.toString(4));
            file.close();
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static JSONTokener leerUnJson(String archivo)
    {
        JSONTokener tokener = null;

        try
        {
            tokener = new JSONTokener(new FileReader(archivo));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return tokener;
    }

    //METODOS PROPIOS

    public static void grabarRepositorioEnJson(Repositorio<Alumno> repoAlumno, Repositorio<Instructor> repoInstructor,
                                               Repositorio<ClaseDeSurf> repoClase, Repositorio<Cliente> repoCliente,
                                               Repositorio<Reserva> repoReserva, Repositorio<Equipo> repoEquipo,
                                               Repositorio<Alquiler> repoAlquiler,
                                               Repositorio<Pago> repoPago, String archivo)
    {
        try
        {
            JSONObject json = new JSONObject();

            json.put("repoAlumnos", coleccion_a_JsonArray(repoAlumno.getTodos()));
            json.put("repoInstructores", coleccion_a_JsonArray(repoInstructor.getTodos()));
            json.put("repoClases", coleccion_a_JsonArray(repoClase.getTodos()));
            json.put("repoClientes", coleccion_a_JsonArray(repoCliente.getTodos()));
            json.put("repoReservas", coleccion_a_JsonArray(repoReserva.getTodos()));
            json.put("repoEquipos", coleccion_a_JsonArray(repoEquipo.getTodos()));
            json.put("repoAlquileres", coleccion_a_JsonArray(repoAlquiler.getTodos()));
            json.put("repoPagos", coleccion_a_JsonArray(repoPago.getTodos()));

            try (FileWriter file = new FileWriter(archivo))
            {
                file.write(json.toString(4));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static JSONArray coleccion_a_JsonArray(Collection<?> coleccion)
    {
        JSONArray jsonArray = new JSONArray();
        for (Object item : coleccion)
        {
            if (item instanceof InterfazJson) // verifica la interfaz
            {
                jsonArray.put(((InterfazJson) item).toJSON());
            }
        }
        return jsonArray;
    }

    public static void leerRepositorioDesdeJson(Repositorio<Alumno> repoAlumno, Repositorio<Instructor> repoInstructor,
                                                Repositorio<ClaseDeSurf> repoClase, Repositorio<Cliente> repoCliente,
                                                Repositorio<Reserva> repoReserva, Repositorio<Equipo> repoEquipo,
                                                Repositorio<Alquiler> repoAlquiler,
                                                Repositorio<Pago> repoPago, String archivo)
    {
        try
        {
            StringBuilder contenidoJson = new StringBuilder();
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo)))
            {
                String linea;
                while ((linea = lector.readLine()) != null)
                {
                    contenidoJson.append(linea);
                }
            }

            JSONObject jObject = new JSONObject(contenidoJson.toString());

            // ORDEN DE CARGA
            // 1. Cargamos lo que NO depende de otros objetos
            cargarPagos(jObject.optJSONArray("repoPagos"), repoPago);
            cargarEquipos(jObject.optJSONArray("repoEquipos"), repoEquipo);
            cargarInstructores(jObject.optJSONArray("repoInstructores"), repoInstructor);
            cargarAlumnos(jObject.optJSONArray("repoAlumnos"), repoAlumno);
            cargarClientes(jObject.optJSONArray("repoClientes"), repoCliente);

            // 2. Cargamos lo que depende de lo anterior
            cargarClases(jObject.optJSONArray("repoClases"), repoClase, repoInstructor);

            // 3. Cargamos las relaciones
            cargarAlquileres(jObject.optJSONArray("repoAlquileres"), repoAlquiler, repoEquipo, repoCliente, repoPago);
            cargarReservas(jObject.optJSONArray("repoReservas"), repoReserva, repoAlumno, repoClase, repoPago);

            System.out.println("Repositorios cargados correctamente desde " + archivo);
        }
        catch (Exception e)
        {
            System.out.println("Error al leer los datos del archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static void cargarPagos(JSONArray jsonArray, Repositorio<Pago> repositorio)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Pago pago = new Pago(obj.getInt("idPago"),
                    MetodoPago.valueOf(obj.getString("metodoPago")),
                    obj.getDouble("monto"));

            pago.setEstadoPago(EstadoPago.valueOf(obj.getString("estadoPago")));
            pago.setFechaPago(LocalDate.parse(obj.getString("fechaPago")));

            repositorio.agregar(obj.getInt("idPago"), pago);
        }
    }

    private static void cargarEquipos(JSONArray jsonArray, Repositorio<Equipo> repositorio)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Equipo equipo = new Equipo(
                    obj.getInt("idEquipo"),
                    NombreEquipo.valueOf(obj.getString("nombre")));

            equipo.setDisponible(obj.getBoolean("disponible"));

            repositorio.agregar(obj.getInt("idEquipo"), equipo);
        }
    }

    private static void cargarInstructores(JSONArray jsonArray, Repositorio<Instructor> repositorio)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Instructor instructor = new Instructor(
                    obj.getInt("idInstructor"),
                    obj.getString("dni"),
                    obj.getString("nombre"),
                    obj.getString("apellido"),
                    obj.getInt("edad"),
                    obj.getString("numeroTel"),
                    obj.getInt("aniosExperiencia")
            );
            repositorio.agregar(obj.getInt("idInstructor"), instructor);
        }
    }

    private static void cargarAlumnos(JSONArray jsonArray, Repositorio<Alumno> repositorio)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Alumno alumno = new Alumno(
                    obj.getInt("idAlumno"), // Usamos el nuevo contructor que recibe el id
                    obj.getString("dni"),
                    obj.getString("nombre"),
                    obj.getString("apellido"),
                    obj.getInt("edad"),
                    obj.getString("numeroTel"),
                    NivelDeSurf.valueOf(obj.getString("nivel")),
                    obj.getInt("cantClasesTomadas")
            );
            repositorio.agregar(obj.getInt("idAlumno"), alumno);
        }
    }

    private static void cargarClientes(JSONArray jsonArray, Repositorio<Cliente> repositorio)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Cliente cliente = new Cliente(
                    obj.getInt("idCliente"),
                    obj.getString("dni"),
                    obj.getString("nombre"),
                    obj.getString("apellido"),
                    obj.getInt("edad"),
                    obj.getString("numeroTel")
            );
            repositorio.agregar(obj.getInt("idCliente"), cliente);
        }
    }

    private static void cargarClases(JSONArray jsonArray, Repositorio<ClaseDeSurf> repoClase, Repositorio<Instructor> repoInstructor)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);

            // Buscamos el instructor que ya cargamos
            Instructor instructor = repoInstructor.buscarPorId(obj.getInt("Instructor"));

            try
            {
                ClaseDeSurf clase = new ClaseDeSurf(
                        obj.getInt("idClase"),
                        instructor,
                        TipoClase.valueOf(obj.getString("TipoDeClase")),
                        LocalDateTime.parse(obj.getString("fechaYhora"))
                );
                //seteo el atributo cupoOcupados desde el json para reconsturirlo
                clase.setCuposOcupados(obj.getInt("cuposOcupados"));
                repoClase.agregar(obj.getInt("idClase"), clase);
            }
            catch (Exception e)
            {
                System.out.println("Omitiendo clase con fecha inválida (pasada).");
            }
        }
    }

    private static void cargarAlquileres(JSONArray jsonArray, Repositorio<Alquiler> repoAlquiler, Repositorio<Equipo> repoEquipo, Repositorio<Cliente> repoCliente, Repositorio<Pago> repoPago)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);

            //primero obtengo el cliente
            Cliente cliente = repoCliente.buscarPorId(obj.getInt("idCliente"));

            if (cliente == null)
            {
                System.out.println("No se enconetr{o el cliente. ");
                continue; //no creo un alquiler sin un cliente
            }

            //CREO EL ALQUILER
            LocalDate fechaFin = LocalDate.parse(obj.getString("fechaFin"));

            int idAlquiler = obj.getInt("idAlquiler");
            Alquiler alquiler = new Alquiler(idAlquiler, fechaFin, cliente); // Usamos el nuevo contructor que recibe el id

            alquiler.setFechaInicio(LocalDate.parse(obj.getString("fechaInicio")));
            alquiler.setEstaActivo(obj.getBoolean("estaActivo"));

            // Vinculamos equipos
            JSONArray equiposArr = obj.getJSONArray("idEquiposAlquilados");
            for (int j = 0; j < equiposArr.length(); j++)
            {
                Equipo equipo = repoEquipo.buscarPorId(equiposArr.getInt(j));
                if (equipo != null)
                {
                    boolean estabaDisponible = equipo.isDisponible();
                    alquiler.agregarEquipo(equipo); // Este metodo fuerza equipo.setDisponible(false) siempre.
                    equipo.setDisponible(estabaDisponible); //por eso aca restauro el estado como estaba.
                }
            }

            // Vinculamos el pago
            if (!obj.isNull("idPago"))
            {
                int idPago = obj.getInt("idPago");
                Pago pago = repoPago.buscarPorId(idPago);
                if (pago != null)
                {
                    alquiler.setPago(pago);
                }
            }

            //regsitro el alquiler en cliente
            cliente.agregarAlquiler(alquiler);

            //registro alquiler en el repositorio
            repoAlquiler.agregar(obj.getInt("idAlquiler"), alquiler);

            //recalculo el monto
            alquiler.calcularMontoTotal();
        }
    }

    private static void cargarReservas(JSONArray jsonArray, Repositorio<Reserva> repoReserva, Repositorio<Alumno> repoAlumno, Repositorio<ClaseDeSurf> repoClase, Repositorio<Pago> repoPago)
    {
        if (jsonArray == null) return;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);

            // Buscamos los objetos que ya cargamos
            Alumno alumno = repoAlumno.buscarPorId(obj.getInt("idAlumno"));
            ClaseDeSurf clase = repoClase.buscarPorId(obj.getInt("idClase"));
            Pago pago = repoPago.buscarPorId(obj.getInt("idPago"));

            if (alumno != null && clase != null)
            {
                Reserva reserva = new Reserva(
                        obj.getInt("idReserva"),
                        alumno,
                        clase);

                if (pago != null)
                {
                    reserva.setPago(pago);
                }

                repoReserva.agregar(obj.getInt("idReserva"), reserva);
            }
        }
    }
}
