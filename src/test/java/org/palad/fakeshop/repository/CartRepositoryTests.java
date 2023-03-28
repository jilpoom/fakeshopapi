package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.controller.exception.NotFoundException;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        int day = 1;

        for(int i = 0; i < 100; i++) {

            User user = userRepository.findById(Long.valueOf(i + 1)).orElseThrow();

            Cart cart = Cart.builder()
                    .date(LocalDate.of(2023, 03, day))
                    .user(user)
                    .products(new ArrayList<>())
                    .build();


            day = day % 31 == 0 ? 1 : day + 1;

            cartRepository.save(cart);
        }



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


    @Test
    @DisplayName("Cart Update")
    public void cartUpdate() {

    }

    @Test
    @DisplayName("날짜 범위별 Cart 가져오기")
    @Transactional
    public void cartsBetweenDate(){
        List<Cart> cartGroup = cartRepository.findByDateBetween(LocalDate.of(2023, 3, 10),
                LocalDate.of(2023, 3, 31)).orElseThrow(() -> new NotFoundException("cart를 찾을 수 없습니다."));

        cartGroup.forEach(cart -> log.info(cart));

    }


}
