package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alquiler
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

    public Alquiler(Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin)
    {
        if (cliente == null)
        {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        if (fechaInicio == null)
        {
            throw new NullPointerException("⚠️: La Fecha de Inicio no puede ser nula.");
        }
        if (fechaFin == null)
        {
            throw new NullPointerException("⚠️: La Fecha de Fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio))
        {
            throw new IllegalArgumentException("⚠️: La fecha de fin (" + fechaFin + ") no puede ser anterior a la fecha de inicio (" + fechaInicio + ").");
        }

        if (fechaInicio.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la fecha actual.");
        }

        this.idAlquiler = ++contador;
        this.cliente = cliente;
        this.equiposAlquilados = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estaActivo = false;
        this.montoTotal = 0;
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
            throw new NullPointerException("El cliente no puede ser nulo.");
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
            throw new NullPointerException("⚠️: La Fecha de Inicio no puede ser nula.");
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
            throw new NullPointerException("⚠️: La Fecha de Fin no puede ser nula.");
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
}
