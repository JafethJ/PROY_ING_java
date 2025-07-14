package com.example.demo.decorator;

import java.util.List;

// Componente base
interface Combo {
    String getDescripcion();
    double getPrecio();
}

// Implementación base: Combo normal
public class extra implements Combo {
    private final String descripcion;
    private final double precio;

    public extra(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}

// Decorador abstracto
abstract class ComboDecorator implements Combo {
    protected Combo combo;

    public ComboDecorator(Combo combo) {
        this.combo = combo;
    }

    @Override
    public String getDescripcion() {
        return combo.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return combo.getPrecio();
    }
}

// Decorador concreto para añadir un extra (de la tabla decoradores)
class ExtraDecorador extends ComboDecorator {
    private final String nombreExtra;
    private final double precioExtra;

    public ExtraDecorador(Combo combo, String nombreExtra, double precioExtra) {
        super(combo);
        this.nombreExtra = nombreExtra;
        this.precioExtra = precioExtra;
    }

    @Override
    public String getDescripcion() {
        return combo.getDescripcion() + " + " + nombreExtra;
    }

    @Override
    public double getPrecio() {
        return combo.getPrecio() + precioExtra;
    }
}

// DTO simple para pasar extras seleccionados desde el frontend
class ExtraDTO {
    private String nombre;
    private double precioExtra;

    public ExtraDTO(String nombre, double precioExtra) {
        this.nombre = nombre;
        this.precioExtra = precioExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioExtra() {
        return precioExtra;
    }
}

// Utilidad para aplicar múltiples extras seleccionados
class ComboBuilder {
    public static Combo construirComboConExtras(Combo base, List<ExtraDTO> extrasSeleccionados) {
        Combo comboDecorado = base;
        if (extrasSeleccionados != null) {
            for (ExtraDTO extra : extrasSeleccionados) {
                comboDecorado = new ExtraDecorador(comboDecorado, extra.getNombre(), extra.getPrecioExtra());
            }
        }
        return comboDecorado;
    }
}