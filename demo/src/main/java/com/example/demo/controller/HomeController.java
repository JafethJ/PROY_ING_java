package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.CategoriaRepository;
import jakarta.servlet.http.HttpSession; 
import com.example.demo.entity.Usuario;


@Controller
public class HomeController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public HomeController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/")
    public String mostrarHome(Model model, HttpSession session) { 
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());
        Usuario usuario = (Usuario) session.getAttribute("usuario"); 
        if (usuario != null) {
            model.addAttribute("usuario", usuario); 
        }
        return "home";
    }

    @GetMapping("/admin/panel")
    public String panelAdmin(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && "ADMIN".equals(usuario.getRol())) {
            return "admin";
        }
        return "redirect:/";
    }

    @GetMapping("/cliente/panel")
    public String panelCliente() {
        return "cliente";
    }
}