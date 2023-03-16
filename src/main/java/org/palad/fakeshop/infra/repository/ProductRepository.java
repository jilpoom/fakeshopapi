package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByCategory(String category);

}
