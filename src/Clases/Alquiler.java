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

public class Alquiler implements InterfazJson
{
    private static int contador = 0;
    private final int idAlquiler;
    private final List<Equipo> equiposAlquilados;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double montoTotal;
    private boolean estaActivo;
    private Pago pago;

    /// CONSTRUCTORES

    public Alquiler()
    {
        this.idAlquiler = ++contador;
        this.equiposAlquilados = new ArrayList<>();
        this.fechaInicio = null;
        this.fechaFin = null;
        this.montoTotal = 0;
        this.estaActivo = false;
        this.pago = new Pago();
    }

    public Alquiler(LocalDate fechaFin, Cliente cliente)
    {
        if (fechaFin == null)
        {
            throw new IllegalArgumentException("⚠️: La Fecha de Fin no puede ser nula.");
        }
        if (fechaFin.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("⚠️: La fecha de fin (" + fechaFin + ") no puede ser anterior a la fecha de inicio (" + LocalDate.now() + ").");
        }

        this.idAlquiler = ++contador;
        this.equiposAlquilados = new ArrayList<>();
        this.cliente = cliente;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = fechaFin;
        this.estaActivo = true;
        this.montoTotal = 0;
        this.pago = new Pago();
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

    public Pago getPago()
    {
        return pago;
    }

    public void setPago(Pago pago)
    {
        this.pago = pago;
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
        pago.setMonto(total);
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
        equipo.setDisponible(true);

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
                " | Periodo: " + fechaInicio + " a " + fechaFin +
                " | Monto: $" + montoTotal +
                " | Activo: " + estaActivo +
                " | Equipos: " + equiposAlquilados.size();
    }

    public void mostrarAlquiler()
    {
        System.out.println("────────── DETALLE DEL ALQUILER ──────────");
        System.out.println("ID: " + idAlquiler);
        System.out.println("Periodo: " + fechaInicio + " a " + fechaFin);
        System.out.println("Estado: " + (estaActivo? "Activo" : "Finalizado"));
        System.out.println("Monto: $" + montoTotal);

        System.out.println("Equipos alquilados: ");
            if (equiposAlquilados == null || equiposAlquilados.isEmpty()){
            System.out.println("No se cargaron equipos");
            }else{
                 for (Equipo equipo : equiposAlquilados){
                System.out.println(" . ID" +  equipo.getIdEquipo() + " - " + equipo.getNombre());
                }
            }
        System.out.println("\nPago: ");
             if (pago != null) {
                System.out.println("  - Método: " + pago.getMetodoPago());
                System.out.println("  - Monto pagado: $" + pago.getMonto());
                System.out.println("  - Fecha de pago: " + pago.getFechaPago());
            } else {
                System.out.println("  - No se registró pago aún.");
            }
        System.out.println("──────────────────────────────────────────────\n");
    }

    public void cancelarAlquiler()
    {
        this.estaActivo = true;
        {
            if(!estaActivo)
            {
                throw new IllegalStateException("El alquiler ya esta cancelado o finalizado.");
            }

            //libero el equipo
            for (Equipo equipo : equiposAlquilados){
                equipo.setDisponible(true);
            }

            this.estaActivo = false;
            this.montoTotal = 0.0;

            System.out.println("El alquiler ha sido cancelado correctamente.");
        }
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jObj = new JSONObject();

        try
        {
            jObj.put("idAlquiler", idAlquiler);

            JSONArray jArray = new JSONArray();
            for (Equipo e : equiposAlquilados)
            {
                jArray.put(e.getIdEquipo());
            }
            jObj.put("idEquiposAlquilados", jArray);

            jObj.put("idCliente", cliente.getIdCliente());
            jObj.put("fechaInicio", fechaInicio != null ? fechaInicio.toString() : JSONObject.NULL);
            jObj.put("fechaFin", fechaFin != null ? fechaFin.toString() : JSONObject.NULL);
            jObj.put("montoTotal", montoTotal);
            jObj.put("estaActivo", estaActivo);
            jObj.put("idPago", pago != null ? pago.getIdPago() : JSONObject.NULL);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jObj;
    }
}
