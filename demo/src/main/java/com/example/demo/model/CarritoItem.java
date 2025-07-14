package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

public class CarritoItem implements Serializable {
    private Integer idProducto;
    private String nombre;
    private Double precioBase;
    private Integer cantidad;
    private boolean agrandado;
    private List<Integer> productosAdicionales;
    private List<Integer> extras;

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecioBase() { return precioBase; }
    public void setPrecioBase(Double precioBase) { this.precioBase = precioBase; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public boolean isAgrandado() { return agrandado; }
    public void setAgrandado(boolean agrandado) { this.agrandado = agrandado; }

    public List<Integer> getProductosAdicionales() { return productosAdicionales; }
    public void setProductosAdicionales(List<Integer> productosAdicionales) { this.productosAdicionales = productosAdicionales; }

    public List<Integer> getExtras() { return extras; }
    public void setExtras(List<Integer> extras) { this.extras = extras; }
}