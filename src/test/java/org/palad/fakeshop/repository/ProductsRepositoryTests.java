package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.ProductsRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Log4j2
public class ProductsRepositoryTests {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Product Insert to Cart")
    public void ProductsInsert() {


        Cart cart = cartRepository.findByUserId(4L);

        Products products = Products
                .builder()
                .productid(3L)
                .quantity(2L)
                .cart(cart)
                .build();


        productsRepository.save(products);

    }


}
