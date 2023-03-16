package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.Cart;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("cart Insert")
    public void cartInsert() {

        User user = userRepository.findById(1L).orElseThrow();

        Cart cart = Cart.builder()
                .date(LocalDate.now())
                .productid(1L)
                .quantity(1L)
                .user(user)
                .build();

        cartRepository.save(cart);


    }

    @Test
    @DisplayName("CART SELECT")
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

//    @Test
//    @DisplayName("CARTS BY USERID")
//    public void cartsByUserID(){
//
//        List<Cart> carts = cartRepository.getCartsByUserid(1L);
//
//        carts.forEach(cart -> log.info(cart));
//
//    }

}
