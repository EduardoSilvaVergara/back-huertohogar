package com.example.huertohogar.repository;

import com.example.huertohogar.model.SuscripcionBoletin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoletinRepository extends JpaRepository<SuscripcionBoletin, Long> {
    Optional<SuscripcionBoletin> findByEmail(String email);
}