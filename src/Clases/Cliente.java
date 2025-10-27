package Clases;

import Interfaces.Pagos;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Pagos
{
    private static int contador = 0;
    private int idCliente;
    private final List<Pago> pagos;
    private final List<Alquiler> alquileres;

    /// CONSTRUCTORES

    public Cliente()
    {
        this.idCliente = ++contador;
        this.pagos = new ArrayList<>();
        this.alquileres = new ArrayList<>();
    }

    public Cliente(int dni, String nombre, String apellido, int edad, int numeroTel)
    {
        super(dni, nombre, apellido, edad, numeroTel);
        this.idCliente = ++contador;
        this.pagos = new ArrayList<>();
        this.alquileres = new ArrayList<>();
    }

    /// GETTERS Y SETTERS

    public static int getContador()
    {
        return contador;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public List<Pago> getPagos()
    {
        return pagos;
    }

    public List<Alquiler> getAlquileres()
    {
        return alquileres;
    }

    /// METODOS

    @Override
    public boolean esMoroso()
    {
        return false;
    }

    @Override
    public boolean pagar(Pago pago)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return super.toString() + " idCliente: " + idCliente;
    }
}
