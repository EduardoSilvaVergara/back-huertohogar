package com.example.huertohogar.service;

import com.example.huertohogar.model.Producto;
import com.example.huertohogar.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarPorColeccion(String collection) {
        return productoRepository.findByCollection(collection);
    }

    public Producto encontrarProductoId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void borrar(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizar(Long id, Producto cambios) {
        Producto p = encontrarProductoId(id);

        if (cambios.getImage() != null) p.setImage(cambios.getImage());
        if (cambios.getSecondImage() != null) p.setSecondImage(cambios.getSecondImage());
        if (cambios.getProductname() != null) p.setProductname(cambios.getProductname());
        if (cambios.getPrice() != null) p.setPrice(cambios.getPrice());
        if (cambios.getOldprice() != null) p.setOldprice(cambios.getOldprice());
        if (cambios.getTag() != null) p.setTag(cambios.getTag());
        if (cambios.getDescription() != null) p.setDescription(cambios.getDescription());
        if (cambios.getSeller() != null) p.setSeller(cambios.getSeller());
        if (cambios.getCollection() != null) p.setCollection(cambios.getCollection());

        return productoRepository.save(p);
    }
}
