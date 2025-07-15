package com.example.demo.pago;

public class PagoYappyStrategy implements MetodoPagoStrategy {
    @Override
    public void procesarPago(PagoRequest request) {
        System.out.println("Pedido pendiente de pago por Yappy para: " + request.getNombreCliente());
    }
}