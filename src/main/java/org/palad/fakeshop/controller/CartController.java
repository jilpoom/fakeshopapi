package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
@Log4j2
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartDTO> getList(@RequestParam(required = false) String limit,
                                 @RequestParam(required = false, defaultValue = "asc") String sort,
                                 @RequestParam(required = false) String startdate,
                                 @RequestParam(required = false) String enddate) {

        if(startdate != null && enddate != null) {
            return cartService.getCartsByDate(startdate, enddate);
        }

        if(limit != null || !sort.equals("asc")) {
            return cartService.getCartsWithLimitAndSort(limit, sort);
        }


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

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST})
    public CartDTO updateProducts(@RequestBody CartDTO cartDTO) {
        return cartService.addProducts(cartDTO);
    }

    @DeleteMapping("/{cid}")
    public CartDTO deleteCart(@PathVariable String cid) {
        return cartService.deleteCart(Long.valueOf(cid));
    }

}
