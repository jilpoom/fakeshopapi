package org.palad.fakeshop.service;


import org.palad.fakeshop.dto.cart.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> getList();

    CartDTO getCart(Long cid);

    CartDTO getUserCart(Long uid);

    CartDTO addProducts(CartDTO cartDTO);

    CartDTO deleteCart(Long cid);

    List<CartDTO> getCartsWithLimitAndSort(String limit, String sort);

    List<CartDTO> getCartsByDate(String startDate, String endDate);
}
