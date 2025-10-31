package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void gabrarRepositorioEnJson(Repositorio<Alumno> repoAlumno, Repositorio<Instructor> repoInstructor,
                                               Repositorio<ClaseDeSurf> repoClase, Repositorio<Cliente> repoCliente, String archivo)
    {
        try
        {
            JSONObject json = new JSONObject();

            json.put("alumnos", coleccion_a_JsonArray(repoAlumno.getTodos()));
            json.put("instructor", coleccion_a_JsonArray(repoInstructor.getTodos()));
            json.put("clases", coleccion_a_JsonArray(repoClase.getTodos()));
            json.put("cliente", coleccion_a_JsonArray(repoCliente.getTodos()));

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
            if (item instanceof Alumno)
            {
                jsonArray.put(((Alumno) item).toJSON());
            }
            if (item instanceof Instructor)
            {
                jsonArray.put(((Instructor) item).toJSON());
            }
            if (item instanceof ClaseDeSurf)
            {
                jsonArray.put(((ClaseDeSurf) item).toJSON());
            }
            if (item instanceof Cliente)
            {
                jsonArray.put(((Cliente) item).toJSON());
            }
        }
        return jsonArray;
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

    //Otra forma
    public static String leer(String archivo)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(archivo + ".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }

}
