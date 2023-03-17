package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("cart Insert")
    public void cartInsert() {

        User user = userRepository.findById(4L).orElseThrow();
        // User에 해당하는 Cart 매핑

        List<Products> list = new ArrayList<>();


        Cart cart = Cart.builder()
                .date(LocalDate.now())
                .user(user)
                .build();


        Cart userCart = cartRepository.save(cart);

    }

    @Test
    @DisplayName("Cart UseCase")
    public void cartUseCase() {


    }

    @Test
    @DisplayName("CART SELECT")
    @Transactional
    public void cartSelect() {

        cartRepository.findAll().forEach(cart -> log.info(cart));


    }

    @Test
    @DisplayName("CART GET BY ID")
    public void cartGetOne() {

        Cart cart = cartRepository.findById(1L).orElseThrow();

        log.info(cart);
    }

    @Test
    @DisplayName("CART DELETE")
    public void cartDelete() {

        cartRepository.deleteById(1L);

    }


}
