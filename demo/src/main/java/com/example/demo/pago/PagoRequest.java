package com.example.demo.pago;

public class PagoRequest {
    private String metodo;
    private String nombreCliente;
    // Agrega otros campos si los necesitas

    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
}