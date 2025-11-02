package Clases;

import Enumeradores.EstadoPago;
import Enumeradores.MetodoPago;
import Interfaces.Pagos;
import Interfaces.ToJson;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente extends Persona implements Pagos, ToJson
{
    private static int contador = 0;
    private final int idCliente;
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

    public List<Pago> getPagos()
    {
        return Collections.unmodifiableList(pagos);
    }

    public List<Alquiler> getAlquileres()
    {
        return Collections.unmodifiableList(alquileres);
    }

    /// METODOS

    @Override
    public boolean esMoroso()
    {
        for (Pago pago : pagos)
        {
            if (pago.getEstadoPago() == EstadoPago.PENDIENTE && LocalDate.now().isAfter(pago.getFechaLimite()))
            {
                return true;// es moroso.
            }
        }
        return false;
    }

    @Override
    public boolean pagar(Pago pago, MetodoPago metodo)
    {
        if (pago == null)
        {
            throw new IllegalArgumentException("⚠️: El pago no puede ser nulo.");
        }
        // Si el pago no está en la lista de pagos del cliente, lo agrega
        if (!this.pagos.contains(pago))
        {
            this.pagos.add(pago);
        }
        // Marca el pago como realizado
        pago.setMetodoPago(metodo);
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago(EstadoPago.REALIZADO);
        return true;
    }

    @Override
    public String toString()
    {
        return super.toString() + " idCliente: " + idCliente;
    }

    public void agregarAlquiler(Alquiler alquiler)
    {
        if (alquiler == null)
        {
            throw new IllegalArgumentException("⚠️: El alquiler no puede ser nulo.");
        }
        this.alquileres.add(alquiler);
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jsonObject = super.toJSON();

        try {

            jsonObject.put("idCliente", idCliente);

            JSONArray jArray = new JSONArray();
            for (Pago p : pagos) {
                jArray.put(p.toJSON());
            }
            jsonObject.put("pagos", jArray);

            JSONArray jArrayAlquileres = new JSONArray();
            for (Alquiler a : alquileres) {
                jArrayAlquileres.put(a.toJSON());
            }

            jsonObject.put("alquileres", jArrayAlquileres);

        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonObject;
    }
}
