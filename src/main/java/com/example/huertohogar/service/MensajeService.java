package com.example.huertohogar.service;

import com.example.huertohogar.dto.MensajeRequest;
import com.example.huertohogar.model.Mensaje;
import com.example.huertohogar.repository.MensajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MensajeService {

    private final MensajeRepository repo;

    public MensajeService(MensajeRepository repo) {
        this.repo = repo;
    }

    public Mensaje crear(MensajeRequest req) {
        if (req == null) throw new IllegalArgumentException("Payload nulo");
        if (isBlank(req.getNombre()) || isBlank(req.getEmail()) || isBlank(req.getMensaje())) {
            throw new IllegalArgumentException("Nombre, email y mensaje son obligatorios");
        }
        Mensaje m = new Mensaje();
        m.setNombre(req.getNombre().trim());
        m.setEmail(req.getEmail().trim());
        m.setMensaje(req.getMensaje().trim());
        return repo.save(m);
    }

    public List<Mensaje> listar() {
        return repo.findAll();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
