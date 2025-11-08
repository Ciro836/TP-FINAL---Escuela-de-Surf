package Clases;

import Interfaces.InterfazJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                                                Repositorio<Pago> repoPago, String archivo){
        try{
            StringBuilder contenidoJson = new StringBuilder();
            try(BufferedReader lector = new BufferedReader(new FileReader(archivo))){
                String linea;
                while((linea = lector.readLine()) != null){
                    contenidoJson.append(linea);
                }
            }

            JSONObject jObject = new JSONObject(contenidoJson.toString());

            if (jObject.has("repoAlumnos"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoAlumnos"), repoAlumno, Alumno.class);
            if (jObject.has("repoInstructores"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoInstructores"), repoInstructor, Instructor.class);
            if (jObject.has("repoClases"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoClases"), repoClase, ClaseDeSurf.class);
            if (jObject.has("repoClientes"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoClientes"), repoCliente, Cliente.class);
            if (jObject.has("repoReservas"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoReservas"), repoReserva, Reserva.class);
            if (jObject.has("repoEquipos"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoEquipos"), repoEquipo, Equipo.class);
            if (jObject.has("repoAlquileres"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoAlquileres"), repoAlquiler, Alquiler.class);
            if (jObject.has("repoPagos"))
                JsonArray_a_Repositorio(jObject.getJSONArray("repoPagos"), repoPago, Pago.class);

            System.out.println("Repositorios cargados correcamente desde " + archivo);
        }catch (Exception e){
            System.out.println("Error al leer los datos del archivo" + e.getMessage());
        }
    }


    // paso por paramentro class<t> clase, para luego poder hacer clase.get para que sepa que tipo de instancias debe crear
    public static <T> void JsonArray_a_Repositorio (JSONArray jsonArray, Repositorio <T> repositorio, Class<? extends T> clase)
    {
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            try {

                Object objeto = clase.getDeclaredConstructor().newInstance();

                if (objeto instanceof InterfazJson) {
                    @SuppressWarnings("unchecked")
                    InterfazJson<T> jObject = (InterfazJson<T>) objeto;

                   //guardo en obj porque el constructor de fromJson devuelve un objeto armado
                   T obj = jObject.fromJSON(jsonObject);
                  //utilizo el metodo getID de la interface implementado en las clases, para pasarle al metodo agregar el id correspondiente de cada instancia
                    repositorio.agregar(jObject.getID(),obj);
                }else{
                    System.out.println("La clase " + clase.getSimpleName() + "no implementa la InterfazJson");
                }
//agrego excepciones de la clase
            } catch (JSONException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException | InvocationTargetException e ) {
                System.out.println("Error creando el objeto" + clase.getSimpleName() + ": " + e.getMessage());
            }
        }
    }


}
