package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarHome() {
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
