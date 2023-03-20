package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.infra.repository.ProductsRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Log4j2
public class ProductsRepositoryTests {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("Product Insert to Cart")
    public void ProductsInsert() {
        Cart cart = cartRepository.findByUserId(4L);

        Products products = Products
                .builder()
                .quantity(3L)
                .cart(cart)
                .build();

        productsRepository.save(products);
    }

    @Test
    @DisplayName("Select existing Products")
    public void ProductsUpdate() {
        Cart cart = cartRepository.findByUserId(4L);

        List<Products> productsList = productsRepository.findAllByCart_Cid(cart.getCid());

        productsList.stream().forEach(products -> log.info(products));
    }


    @Test
    @DisplayName("Products Update")
    public void productsUpdate() {

        Cart cart = cartRepository.findByUserId(4L);

        List<Products> products = productsRepository.findAllByCart_Cid(cart.getCid());

        products.add(Products.builder()
                        .quantity(3L)
                        .cart(cart)
                        .productid(productRepository.findById(4L).orElseThrow())
                .build());

        productsRepository.saveAll(products);

        Cart updateCart = cartRepository.findByUserId(4L);

        log.info(updateCart);
    }


}
