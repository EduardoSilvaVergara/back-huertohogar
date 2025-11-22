    package com.example.huertohogar.controller;

    import com.example.huertohogar.model.Producto;
    import com.example.huertohogar.service.ProductoService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/producto")
    @CrossOrigin(origins = "http://localhost:5173")
    public class ProductoController {

        private final ProductoService productoService;
        public ProductoController(ProductoService productoService) {
            this.productoService = productoService;
        }

        @GetMapping
        public ResponseEntity<List<Producto>> listar(@RequestParam(required = false) String collection) {
            List<Producto> productos = (collection != null && !collection.isBlank())
                    ? productoService.listarPorColeccion(collection)
                    : productoService.listarProductos();

            if (productos.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(productos);
        }

        @PostMapping
        public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
            Producto productoNuevo = productoService.guardar(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Producto> buscar(@PathVariable Long id) {
            try {
                return ResponseEntity.ok(productoService.encontrarProductoId(id));
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
            try {
                Producto actualizado = productoService.actualizar(id, producto);
                return ResponseEntity.ok(actualizado);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            try {
                productoService.borrar(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    }
