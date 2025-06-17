package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para hashear la contraseña
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Hashea la contraseña antes de buscar en la base de datos
        String hashedPassword = hashPassword(request.getContrasena());
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoAndContrasena(
                request.getCorreo(), hashedPassword);

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

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        // Verifica si el correo ya existe
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        // Hashea la contraseña antes de guardar
        usuario.setContrasena(hashPassword(request.getContrasena()));

        // Asignar rol automáticamente según el dominio del correo
        String correo = request.getCorreo().toLowerCase();
        if (correo.endsWith("@empresa.ac.pa")) {
            usuario.setRol("ADMIN");
        } else {
            usuario.setRol("CLIENTE");
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    // Clase interna para recibir los datos del registro
    public static class RegistroRequest {
        private String nombre;
        private String correo;
        private String contrasena;

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }
        public String getContrasena() { return contrasena; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    }
}