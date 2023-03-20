package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query("SELECT c FROM Cart c WHERE c.user.uid = :uid")
    Cart findByUserId(Long uid);


}
