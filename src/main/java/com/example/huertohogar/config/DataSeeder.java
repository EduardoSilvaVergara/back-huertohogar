// backend/src/main/java/com/example/huertohogar/config/DataSeeder.java
package com.example.huertohogar.config;

import com.example.huertohogar.model.Producto;
import com.example.huertohogar.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                // ========== FRUTAS (2) ==========
                Producto f1 = new Producto();
                f1.setImage("/Frutas/manzana-1.jpg");
                f1.setSecondImage("/Frutas/manzana-2.jpg");
                f1.setProductname("Manzana Roja (por kilo)");
                f1.setPrice("1200");
                f1.setOldprice("1400");
                f1.setTag("New");
                f1.setDescription("Fruta crujiente y dulce, ideal para colaciones y postres.");
                f1.setSeller("Frutas del Valle");
                f1.setCollection("Frutas Frescas");
                repo.save(f1);

                Producto f2 = new Producto();
                f2.setImage("/Frutas/platano-1.jpg");
                f2.setSecondImage("/Frutas/platano-2.jpg");
                f2.setProductname("Plátano Maduro (por kilo)");
                f2.setPrice("800");
                f2.setOldprice("950");
                f2.setTag("Sale");
                f2.setDescription("Plátanos maduros perfectos para smoothies y repostería.");
                f2.setSeller("Frutas del Valle");
                f2.setCollection("Frutas Frescas");
                repo.save(f2);

                // ========== VERDURAS (2) ==========
                Producto v1 = new Producto();
                v1.setImage("/Verduras/zanahoria1.jpg");
                v1.setSecondImage("/Verduras/zanahoria2.jpg");
                v1.setProductname("Zanahoria Fresca (por kilo)");
                v1.setPrice("900");
                v1.setOldprice("1000");
                v1.setTag("New");
                v1.setDescription("Zanahorias crujientes, ricas en betacarotenos para tus ensaladas.");
                v1.setSeller("Huerto Natural");
                v1.setCollection("Verduras Frescas");
                repo.save(v1);

                Producto v2 = new Producto();
                v2.setImage("/Verduras/lechuga1.jpg");
                v2.setSecondImage("/Verduras/lechuga2.jpg");
                v2.setProductname("Lechuga Escarola (unidad)");
                v2.setPrice("700");
                v2.setOldprice("800");
                v2.setTag("Sale");
                v2.setDescription("Lechuga fresca y crocante, ideal para tus ensaladas diarias.");
                v2.setSeller("Huerto Natural");
                v2.setCollection("Verduras Frescas");
                repo.save(v2);

                // ========== LACTEOS (2) ==========
                Producto l1 = new Producto();
                l1.setImage("/Lacteos/LecheEntera1.jpg");
                l1.setSecondImage("/Lacteos/LecheEntera2.jpg");
                l1.setProductname("Leche Entera 1L");
                l1.setPrice("1200");
                l1.setOldprice("1300");
                l1.setTag("New");
                l1.setDescription("Leche entera pasteurizada, perfecta para el desayuno.");
                l1.setSeller("Lácteos del Sur");
                l1.setCollection("Lacteos");
                repo.save(l1);

                Producto l2 = new Producto();
                l2.setImage("/Lacteos/quesilloFresco1.jpg");
                l2.setSecondImage("/Lacteos/quesilloFresco2.jpg");
                l2.setProductname("Quesillo Fresco");
                l2.setPrice("3800");
                l2.setOldprice("4500");
                l2.setTag("Sale");
                l2.setDescription("Queso fresco y suave, ideal para ensaladas y desayunos.");
                l2.setSeller("Lácteos del Sur");
                l2.setCollection("Lacteos");
                repo.save(l2);

                // ========== ORGANICOS (2) ==========
                Producto o1 = new Producto();
                o1.setImage("/Organicos/aceiteOliva1.jpg");
                o1.setSecondImage("/Organicos/aceiteOliva2.jpg");
                o1.setProductname("Aceite de Oliva Extra Virgen 500ml");
                o1.setPrice("6800");
                o1.setOldprice("7500");
                o1.setTag("Sale");
                o1.setDescription("Aceite prensado en frío, perfecto para ensaladas y cocina saludable.");
                o1.setSeller("EcoHuerto");
                o1.setCollection("Organicos");
                repo.save(o1);

                Producto o2 = new Producto();
                o2.setImage("/Organicos/nueces1.jpg");
                o2.setSecondImage("/Organicos/nueces2.jpg");
                o2.setProductname("Nueces Orgánicas 1kg");
                o2.setPrice("8500");
                o2.setOldprice("9500");
                o2.setTag("New");
                o2.setDescription("Ricas en omega 3, ideales para snacks o repostería.");
                o2.setSeller("EcoHuerto");
                o2.setCollection("Organicos");
                repo.save(o2);
            }
        };
    }
}