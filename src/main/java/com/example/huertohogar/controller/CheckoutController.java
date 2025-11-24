package com.example.huertohogar.controller;

import com.example.huertohogar.dto.PedidoRequest;
import com.example.huertohogar.model.Boleta;
import com.example.huertohogar.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checkout")
@CrossOrigin(origins = "http://localhost:5173")
public class CheckoutController {

    private final CheckoutService service;

    public CheckoutController(CheckoutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PedidoRequest req) {
        try {
            Boleta boleta = service.crearBoleta(req);
            return ResponseEntity.ok(boleta);
        } catch (IllegalArgumentException e) {
            // 400 Bad Request con JSON de error
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        } catch (Exception e) {
            // 500 Internal Server Error con JSON de error
            return ResponseEntity.status(500).body(
                Map.of("error", "Error interno del servidor")
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> listar() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Boleta b = service.obtenerPorId(id);
        if (b == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Boleta no encontrada"));
        }
        return ResponseEntity.ok(b);
    }
}


