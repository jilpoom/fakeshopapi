package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
