package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "decoradores")
public class Decorador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_decorador")
    private Integer idDecorador;

    private String nombre;

    @Column(name = "precio_extra")
    private Double precioExtra;

    @Column(name = "id_producto")
    private Integer idProducto;

    // Getters y setters
    public Integer getIdDecorador() {
        return idDecorador;
    }

    public void setIdDecorador(Integer idDecorador) {
        this.idDecorador = idDecorador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(Double precioExtra) {
        this.precioExtra = precioExtra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}