package com.example.huertohogar.controller;

import com.example.huertohogar.dto.PedidoRequest;
import com.example.huertohogar.model.Boleta;
import com.example.huertohogar.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boleta> crear(@RequestBody PedidoRequest req) {
        return ResponseEntity.ok(service.crearBoleta(req));
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> listar() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> obtenerPorId(@PathVariable Long id) {
        Boleta b = service.obtenerPorId(id);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(b);
    }
}
