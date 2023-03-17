package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.palad.fakeshop.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    private final ModelMapper modelMapper;

    @Override
    public List<CartDTO> getList() {

        List<Cart> list = cartRepository.findAll();


        List<CartDTO> dtoList = list.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());


        return dtoList;
    }

    @Override
    public CartDTO getCart(Long cid) {

        Cart cart = cartRepository.findById(cid).orElseThrow();

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return cartDTO;
    }

    @Override
    public CartDTO getUserCart(Long uid) {

        Cart cart = cartRepository.findByUserId(uid);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return cartDTO;
    }
}
