package com.example.huertohogar.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "boleta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision", columnDefinition = "DATETIME(0)")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    // Datos cliente
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;

    @Column(precision = 12, scale = 2)
    private BigDecimal neto;

    @Column(precision = 12, scale = 2)
    private BigDecimal iva;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BoletaItem> items;
}