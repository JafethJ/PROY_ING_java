package com.example.demo.pago;

import org.springframework.stereotype.Service;

@Service
public class PagoService {
    public void procesarPago(PagoRequest request) {
        MetodoPagoStrategy strategy;
        switch (request.getMetodo()) {
            case "yappy":
                strategy = new PagoYappyStrategy();
                break;
            case "efectivo":
                strategy = new PagoEfectivoStrategy();
                break;
            default:
                throw new IllegalArgumentException("Método de pago no soportado");
        }
        strategy.procesarPago(request);
    }
}
