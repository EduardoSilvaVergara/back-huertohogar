package com.example.huertohogar.controller;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.huertohogar.model.Usuario;
import com.example.huertohogar.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "http://localhost:5173") 
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        // Por defecto, todos los nuevos usuarios son USER
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USER");
        }
        Usuario usuarioNuevo = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }
    
    // NUEVO ENDPOINT DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("contraseña");
            
            Usuario usuario = usuarioService.encontrarPorEmail(email);
            
            if (usuario != null && usuario.getContraseña().equals(password)) {
                // Login exitoso - devolvemos el usuario sin la contraseña
                Map<String, Object> response = new HashMap<>();
                response.put("id", usuario.getId());
                response.put("nombre", usuario.getNombre());
                response.put("email", usuario.getEmail());
                response.put("rol", usuario.getRol());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Usuario no encontrado"));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.encontrarUsuarioId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario u = usuarioService.encontrarUsuarioId(id);
            u.setNombre(usuario.getNombre());
            u.setEmail(usuario.getEmail());
            u.setContraseña(usuario.getContraseña());
            if (usuario.getRol() != null && !usuario.getRol().isEmpty()) {
                u.setRol(usuario.getRol());
            }
            usuarioService.guardar(u);
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.borrar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}