package Utiles;

import Clases.*;
import Interfaces.InterfazJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
}
