package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.cart.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
