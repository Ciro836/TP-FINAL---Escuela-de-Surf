package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Alquiler(Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, double montoTotal, boolean estaActivo)
    {
        this.idAlquiler = ++contador;
        this.cliente = cliente;
        this.equiposAlquilados = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoTotal = montoTotal;
        this.estaActivo = estaActivo;
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
        this.cliente = cliente;
    }

    public List<Equipo> getEquiposAlquilados()
    {
        return equiposAlquilados;
    }

    public LocalDate getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public double getMontoTotal()
    {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal)
    {
        this.montoTotal = montoTotal;
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

    public double calcularMontoTotal()
    {
        return montoTotal;
    }

    public void finalizarAlquiler()
    {
    }

    public void mostrarDetalle()
    {

    }
}
