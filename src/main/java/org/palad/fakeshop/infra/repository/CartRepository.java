package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user.uid = :uid")
    Cart findByUserId(Long uid);

    @Query("SELECT c from Cart c JOIN FETCH c.user WHERE c.date BETWEEN :startDate AND :endDate ORDER BY c.date")
    @EntityGraph(attributePaths = "products")
    Optional<List<Cart>> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c FROM Cart c JOIN FETCH c.user")
    @EntityGraph(attributePaths = "products", type = EntityGraph.EntityGraphType.FETCH)
    List<Cart> findAll();

    @EntityGraph(attributePaths = "products", type = EntityGraph.EntityGraphType.FETCH)
    Page<Cart> findAll(Pageable pageable);
}
