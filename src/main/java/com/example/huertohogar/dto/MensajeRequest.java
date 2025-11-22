// backend/src/main/java/com/example/huertohogar/dto/MensajeRequest.java
package com.example.huertohogar.dto;

import lombok.Data;

@Data
public class MensajeRequest {
    private String nombre;
    private String email;
    private String mensaje;
}