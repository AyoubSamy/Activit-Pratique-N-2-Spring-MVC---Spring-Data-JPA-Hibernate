package net.ayoub.ap2springmvc.repository;

import net.ayoub.ap2springmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
