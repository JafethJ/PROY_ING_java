package com.example.demo.decorator;



// Implementación base: Combo normal
public class tipo implements Combo {
    private final String descripcion;
    private final double precio;

    public tipo(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public String getDescripcion() {
        return descripcion + " (Normal)";
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}


// Decorador concreto: Combo Agrandado
class ComboAgrandado extends ComboDecorator {
    public ComboAgrandado(Combo combo) {
        super(combo);
    }

    @Override
    public String getDescripcion() {
        String desc = combo.getDescripcion();
        if (desc.contains("Normal")) {
            return desc.replace("Normal", "Agrandado");
        } else {
            return desc + " (Agrandado)";
        }
    }

    @Override
    public double getPrecio() {
        return combo.getPrecio() + 2.50;
    }
}