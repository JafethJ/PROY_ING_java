package com.example.demo.pago;

public interface MetodoPagoStrategy {
    void procesarPago(PagoRequest request);
}