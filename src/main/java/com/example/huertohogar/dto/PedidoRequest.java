package com.example.huertohogar.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoRequest {
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private BigDecimal total;
    private List<Item> items;

    @Data
    public static class Item {
        private Long productoId;
        private String nombre;
        private String categoria;
        private String imagen;
        private Integer cantidad;
        private BigDecimal precioUnitario;
    }
}