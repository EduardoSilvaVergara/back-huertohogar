package com.example.huertohogar.service;

import com.example.huertohogar.dto.PedidoRequest;
import com.example.huertohogar.model.Boleta;
import com.example.huertohogar.model.BoletaItem;
import com.example.huertohogar.repository.BoletaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CheckoutService {

    private final BoletaRepository boletaRepo;

    public CheckoutService(BoletaRepository boletaRepo) {
        this.boletaRepo = boletaRepo;
    }

    public Boleta crearBoleta(PedidoRequest req) {
        if (req == null) throw new IllegalArgumentException("Payload nulo");
        List<PedidoRequest.Item> reqItems = req.getItems();
        if (reqItems == null || reqItems.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener items");
        }

        BigDecimal total = req.getTotal() != null ? req.getTotal() : BigDecimal.ZERO;

        BigDecimal divisor = new BigDecimal("1.19");
        BigDecimal neto = total.divide(divisor, 2, RoundingMode.HALF_UP);
        BigDecimal iva = total.subtract(neto);

        Boleta boleta = new Boleta();
        boleta.setNombre(n(req.getNombre()));
        boleta.setEmail(n(req.getEmail()));
        boleta.setTelefono(n(req.getTelefono()));
        boleta.setDireccion(n(req.getDireccion()));
        boleta.setCiudad(n(req.getCiudad()));
        boleta.setTotal(total);
        boleta.setNeto(neto);
        boleta.setIva(iva);

        var items = reqItems.stream().map(i -> {
            BoletaItem bi = new BoletaItem();
            bi.setProductoId(i.getProductoId());
            bi.setNombre(n(i.getNombre()));
            bi.setCantidad(i.getCantidad() != null ? i.getCantidad() : 1);
            bi.setPrecioUnitario(i.getPrecioUnitario() != null ? i.getPrecioUnitario() : BigDecimal.ZERO);
            bi.setTotalLinea(bi.getPrecioUnitario().multiply(BigDecimal.valueOf(bi.getCantidad())));
            bi.setCategoria(n(i.getCategoria()));
            bi.setImagen(n(i.getImagen()));
            bi.setBoleta(boleta);
            return bi;
        }).collect(Collectors.toList());

        boleta.setItems(items);

        return boletaRepo.save(boleta);
    }


    public List<Boleta> obtenerTodas() {
        return boletaRepo.findAll();
    }

    public Boleta obtenerPorId(Long id) {
        return boletaRepo.findById(id).orElse(null);
    }

    // Helper interno
    private String n(String s) { return s == null ? "" : s.trim(); }
}
