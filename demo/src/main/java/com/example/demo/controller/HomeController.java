package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.repository.ProductoRepository;

@Controller
public class HomeController {

    private final ProductoRepository productoRepository;

    public HomeController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {
        var productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "home";
    }

    @GetMapping("/admin/panel")
    public String panelAdmin() {
        return "admin";
    }

    @GetMapping("/cliente/panel")
    public String panelCliente() {
        return "cliente";
    }
}
