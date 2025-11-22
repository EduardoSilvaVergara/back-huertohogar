// backend/src/main/java/com/example/huertohogar/repository/MensajeRepository.java
package com.example.huertohogar.repository;

import com.example.huertohogar.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> { }