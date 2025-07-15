package com.example.demo.pago;

public class PagoEfectivoStrategy implements MetodoPagoStrategy {
    @Override
    public void procesarPago(PagoRequest request) {
        System.out.println("Pedido pendiente de pago en efectivo para: " + request.getNombreCliente());
    }
}
