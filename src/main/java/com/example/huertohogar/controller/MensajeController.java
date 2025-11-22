package com.example.huertohogar.controller;

import com.example.huertohogar.dto.MensajeRequest;
import com.example.huertohogar.model.Mensaje;
import com.example.huertohogar.service.MensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensaje")
@CrossOrigin(origins = "http://localhost:5173")
public class MensajeController {

    private final MensajeService service;

    public MensajeController(MensajeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody MensajeRequest req) {
        return ResponseEntity.ok(service.crear(req));
    }

    @GetMapping
    public ResponseEntity<List<Mensaje>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
