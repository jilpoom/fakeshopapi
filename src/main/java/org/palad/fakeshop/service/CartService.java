package org.palad.fakeshop.service;


import org.palad.fakeshop.dto.cart.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> getList();

    CartDTO getCart(Long cid);

    CartDTO getUserCart(Long uid);
}
