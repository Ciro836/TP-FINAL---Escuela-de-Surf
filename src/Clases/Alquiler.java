package Clases;

import Interfaces.InterfazJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alquiler implements InterfazJson<Alquiler>
{
    private static int contador = 0;
    private final int idAlquiler;
    private Cliente cliente;
    private final List<Equipo> equiposAlquilados;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double montoTotal;
    private boolean estaActivo;

    /// CONSTRUCTORES

    public Alquiler()
    {
        this.idAlquiler = ++contador;
        this.cliente = new Cliente();
        this.equiposAlquilados = new ArrayList<>();
        this.fechaInicio = null;
        this.fechaFin = null;
        this.montoTotal = 0;
        this.estaActivo = false;
    }

    public Alquiler(Cliente cliente, LocalDate fechaFin)
    {
        if (cliente == null)
        {
            throw new IllegalArgumentException("⚠️: El cliente no puede ser nulo.");
        }
        if (fechaFin == null)
        {
            throw new IllegalArgumentException("⚠️: La Fecha de Fin no puede ser nula.");
        }
        if (fechaFin.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("⚠️: La fecha de fin (" + fechaFin + ") no puede ser anterior a la fecha de inicio (" + LocalDate.now() + ").");
        }

        this.idAlquiler = ++contador;
        this.cliente = cliente;
        this.equiposAlquilados = new ArrayList<>();
        this.fechaInicio = LocalDate.now();
        this.fechaFin = fechaFin;
        this.estaActivo = true;
        this.montoTotal = 0;
    }
    // constructor para JSON
    public Alquiler(int idAlquiler, Cliente cliente, List<Equipo> equiposAlquilados, LocalDate fechaInicio, LocalDate fechaFin, double monto, boolean estaActivo)
    {
        this.idAlquiler = idAlquiler;
        this.cliente = cliente;
        this.equiposAlquilados = equiposAlquilados;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoTotal = monto;
        this.estaActivo = estaActivo;

        if (idAlquiler > contador){
            contador = idAlquiler;
        }
    }


    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdAlquiler()
    {
        return idAlquiler;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        if (cliente == null)
        {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    public List<Equipo> getEquiposAlquilados()
    {
        return Collections.unmodifiableList(equiposAlquilados);
    }

    public LocalDate getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio)
    {
        if (fechaInicio == null)
        {
            throw new IllegalArgumentException("⚠️: La Fecha de Inicio no puede ser nula.");
        }
        if (fechaInicio.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la fecha actual.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin)
    {
        if (fechaFin == null)
        {
            throw new IllegalArgumentException("⚠️: La Fecha de Fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio))
        {
            throw new IllegalArgumentException("⚠️: La fecha de fin (" + fechaFin + ") no puede ser anterior a la fecha de inicio (" + fechaInicio + ").");
        }
        this.fechaFin = fechaFin;
    }

    public double getMontoTotal()
    {
        return montoTotal;
    }

    public boolean isEstaActivo()
    {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo)
    {
        this.estaActivo = estaActivo;
    }

    /// METODOS

    public int contarDiasDeAlquiler()
    {
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
    }

    public void calcularMontoTotal()
    {
        double total = 0.0;
        int diasAlquiler = contarDiasDeAlquiler();

        for (Equipo equipo : equiposAlquilados)
        {
            total += equipo.getPrecioPorDia() * diasAlquiler;
        }

        this.montoTotal = total;
    }

    public void finalizarAlquiler()
    {
        if (!this.estaActivo)
        {
            throw new IllegalStateException("El alquiler ya está inactivo.");
        }

        this.setEstaActivo(false);

        for (Equipo equipo : equiposAlquilados)
        {
            equipo.setDisponible(true);
        }
    }

    public void agregarEquipo(Equipo equipo)
    {
        if (equipo == null)
        {
            throw new IllegalArgumentException("No se puede agregar un equipo nulo al alquiler.");
        }

        this.equiposAlquilados.add(equipo);

        equipo.setDisponible(false);

        //aca se recalcula el monto total
        calcularMontoTotal();
    }

    public boolean eliminarEquipo(Equipo equipo)
    {
        boolean removido = this.equiposAlquilados.remove(equipo);

        // Solo se recalcula el monto si realmente se eliminó algo
        if (removido)
        {
            calcularMontoTotal();
        }
        return removido;
    }

    @Override
    public String toString()
    {
        return "ALQUILER: ID=" + idAlquiler +
                " | Cliente: " + cliente.getNombre() +
                " | Periodo: " + fechaInicio + " a " + fechaFin +
                " | Monto: $" + montoTotal +
                " | Activo: " + estaActivo +
                " | Equipos: " + equiposAlquilados.size();
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jObj = new JSONObject();

        try
        {

            jObj.put("idAlquiler", idAlquiler);
            jObj.put("cliente", cliente != null ? cliente.getIdCliente() : JSONObject.NULL);

            JSONArray jArray = new JSONArray();
            for (Equipo e : equiposAlquilados)
            {
                jArray.put(e.getIdEquipo());
            }
            jObj.put("idEquiposAlquilados", jArray);

            jObj.put("fechaInicio", fechaInicio != null ? fechaInicio.toString() : JSONObject.NULL);
            jObj.put("fechaFin", fechaFin != null ? fechaFin.toString() : JSONObject.NULL);
            jObj.put("montoTotal", montoTotal);
            jObj.put("estaActivo", estaActivo);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jObj;
    }

    @Override
    public Alquiler fromJSON(JSONObject objeto)
    {
        int id = objeto.getInt("idAlquiler");

        Cliente cliente = null;
        if (!objeto.isNull("cliente")) {
            int idCliente = objeto.getInt("cliente");
        }

        //solo paso los id de los equipos alquilados, en vez del objeto completo. Lo mismo hice con cliente

        List<Equipo> equiposAlquilados = new ArrayList<>();
        JSONArray jsonArrayEquipos =  objeto.getJSONArray("idEquiposAlquilados");
        for (int i = 0; i < jsonArrayEquipos.length(); i++){
                int idEquipos = jsonArrayEquipos.getInt(i);
        }

        LocalDate fechaInicio = null;
        if (!objeto.isNull("fechaInicio")) {
            fechaInicio = LocalDate.parse(objeto.getString("fechaInicio"));
        }

        LocalDate fechaFin = null;
        if (!objeto.isNull("fechaFin")) {
            fechaFin = LocalDate.parse(objeto.getString("fechaFin"));
        }

        double montoTotal = objeto.getDouble("montoTotal");
        boolean estaActivo = objeto.getBoolean("estaActivo");

        return new Alquiler(id, cliente, equiposAlquilados, fechaInicio, fechaInicio, montoTotal, estaActivo);
    }

    public int getID(){
        return idAlquiler;
    }
}
