package com.example.huertohogar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String secondImage;

    @Column(name = "productname", nullable = false)
    private String productname;

    @Column(nullable = false)
    private String price;

    private String oldprice;

    private String tag;

    private String description;

    private String seller;
    
    private String collection;
}