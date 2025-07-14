package com.example.demo.controller;

import com.example.demo.model.CarritoItem;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestBody CarritoItem item, HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        carrito.add(item);
        session.setAttribute("carrito", carrito);
        return "ok";
    }

    @GetMapping
    public List<CarritoItem> verCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();
        return carrito;
    }
}