package net.ayoub.ap2springmvc;

import net.ayoub.ap2springmvc.entities.Product;
import net.ayoub.ap2springmvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class Ap2SpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(Ap2SpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(5500.0)
                    .quantite(4)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(3200.0)
                    .quantite(2)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smart Phone")
                    .price(6000.0)
                    .quantite(5)
                    .build());

            productRepository.findAll().forEach(product ->{
                System.out.println(product.toString());
            });
        };
    }

}
