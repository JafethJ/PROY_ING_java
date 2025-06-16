package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoAndContrasena(
                request.getCorreo(), request.getContrasena());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if ("ADMIN".equalsIgnoreCase(usuario.getRol())) {
                return ResponseEntity.ok("/admin/panel");
            } else if ("CLIENTE".equalsIgnoreCase(usuario.getRol())) {
                return ResponseEntity.ok("/cliente/panel");
            } else {
                return ResponseEntity.badRequest().body("Rol desconocido");
            }
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    // Clase interna para recibir los datos del login
    public static class LoginRequest {
        private String correo;
        private String contrasena;

        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }
        public String getContrasena() { return contrasena; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    }
}