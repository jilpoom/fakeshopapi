package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findAllByCart_Cid(Long cid);

    Products findByProductidAndCart(Product productid, Cart cart);

}
