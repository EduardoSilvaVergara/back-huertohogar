package com.example.huertohogar.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.huertohogar.model.Usuario;
import com.example.huertohogar.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario encontrarUsuarioId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    // NUEVO MÉTODO para buscar por email
    public Usuario encontrarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    public Usuario guardar(Usuario usuario) {
        // Aquí hashea contraseña si usas BCrypt
        return usuarioRepository.save(usuario);
    }
    
    public void borrar(Long id) {
        usuarioRepository.deleteById(id);
    }
}