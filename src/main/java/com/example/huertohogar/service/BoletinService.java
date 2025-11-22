package com.example.huertohogar.service;

import com.example.huertohogar.model.SuscripcionBoletin;
import com.example.huertohogar.repository.BoletinRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BoletinService {

    private final BoletinRepository repo;

    public BoletinService(BoletinRepository repo) {
        this.repo = repo;
    }

    public SuscripcionBoletin suscribir(String email) {
        if (isBlank(email)) throw new IllegalArgumentException("Email obligatorio");
        String clean = email.trim().toLowerCase();

        // Retorna existente si ya está suscrito
        var existente = repo.findByEmail(clean);
        if (existente.isPresent()) return existente.get();

        try {
            SuscripcionBoletin s = new SuscripcionBoletin();
            s.setEmail(clean);
            return repo.save(s);
        } catch (DataIntegrityViolationException ex) {
            // En caso de condición de carrera
            return repo.findByEmail(clean).orElseThrow(() -> ex);
        }
    }

    public List<SuscripcionBoletin> listar() {
        return repo.findAll();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
