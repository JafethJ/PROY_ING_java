package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CarritoItem {
    
    private Integer idProducto;
    
    @JsonProperty("nombre")
    private String nombreProducto;
    
    @JsonProperty("precioBase")
    private Double precioUnitario;
    
    private Double precioAgrandado;
    private boolean agrandado;
    private Integer cantidad;
    private String imagen;
    private List<ProductoAdicional> productosAdicionales;
    private List<Extra> extras;

    // Constructores
    public CarritoItem() {
        this.precioUnitario = 0.0;
        this.precioAgrandado = 0.0;
        this.cantidad = 1;
        this.agrandado = false;
    }

    // Getters y setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioAgrandado() {
        return precioAgrandado;
    }

    public void setPrecioAgrandado(Double precioAgrandado) {
        this.precioAgrandado = precioAgrandado;
    }

    public boolean isAgrandado() {
        return agrandado;
    }

    public void setAgrandado(boolean agrandado) {
        this.agrandado = agrandado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<ProductoAdicional> getProductosAdicionales() {
        return productosAdicionales;
    }

    public void setProductosAdicionales(List<ProductoAdicional> productosAdicionales) {
        this.productosAdicionales = productosAdicionales;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public Double calcularPrecioTotal() {
    // Precio base comienza con precio unitario
    Double precioBase = (precioUnitario != null) ? precioUnitario : 0.0;
    
    // Si está agrandado, sumar 2.5 al precio base
    if (agrandado) {
        precioBase += 2.5;
    }
    
    // Validar cantidad
    int cant = (cantidad != null && cantidad > 0) ? cantidad : 1;
    
    double total = precioBase * cant;

    // Sumar extras si existen
    if (extras != null && !extras.isEmpty()) {
        for (Extra extra : extras) {
            if (extra != null && extra.getPrecio() != null) {
                total += extra.getPrecio() * cant;
            }
        }
    }

    return total;
}

    @Override
    public String toString() {
        return "CarritoItem{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", precioAgrandado=" + precioAgrandado +
                ", agrandado=" + agrandado +
                ", cantidad=" + cantidad +
                ", imagen='" + imagen + '\'' +
                ", productosAdicionales=" + productosAdicionales +
                ", extras=" + extras +
                '}';
    }
}