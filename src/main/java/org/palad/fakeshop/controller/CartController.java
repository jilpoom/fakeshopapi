package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.dto.cart.ProductsDTO;
import org.palad.fakeshop.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
@Log4j2
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartDTO> getList() {
        return cartService.getList();
    }

    @GetMapping("/{cid}")
    public CartDTO getCart(@PathVariable String cid) {
        return cartService.getCart(Long.valueOf(cid));
    }

    @GetMapping("/user/{uid}")
    public CartDTO getUserCart(@PathVariable String uid) {

        return cartService.getUserCart(Long.valueOf(uid));

    }
}
