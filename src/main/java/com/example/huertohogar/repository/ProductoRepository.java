package com.example.huertohogar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.huertohogar.model.Producto;
import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCollection(String collection);  // Ej: para filtrar por "Frutas Frescas"
}