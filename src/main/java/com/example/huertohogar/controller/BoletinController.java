package com.example.huertohogar.controller;

import com.example.huertohogar.dto.BoletinRequest;
import com.example.huertohogar.model.SuscripcionBoletin;
import com.example.huertohogar.service.BoletinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boletin")
@CrossOrigin(origins = "http://localhost:5173")
public class BoletinController {

    private final BoletinService service;

    public BoletinController(BoletinService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SuscripcionBoletin> suscribir(@RequestBody BoletinRequest req) {
        return ResponseEntity.ok(service.suscribir(req.email()));
    }

    @GetMapping
    public ResponseEntity<List<SuscripcionBoletin>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
