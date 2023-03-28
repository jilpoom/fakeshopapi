package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.controller.exception.NotFoundException;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.dto.cart.ProductsDTO;
import org.palad.fakeshop.infra.repository.CartRepository;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.infra.repository.ProductsRepository;
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

    private final ProductsRepository productsRepository;

    private final ProductRepository productRepository;

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
        Cart cart = cartRepository.findById(cid).orElseThrow(() -> new NotFoundException("해당 카트를 찾을 수 없습니다. : " + cid));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return cartDTO;
    }

    @Override
    public CartDTO getUserCart(Long uid) {
        Cart cart = cartRepository.findByUserId(uid);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return cartDTO;
    }

    @Override
    public CartDTO addProducts(CartDTO cartDTO) {
        // 1. CartDTO -> ProductsDTO 추출
        List<ProductsDTO> productsDTOList = cartDTO.getProducts();

        // 2. ProductsDTO -> Products 매핑하면서, 해당 cart에 있는 psid 가져오기

        List<Products> productList = productsDTOList.stream().map(
                productsDTO -> {
                    Cart cart = cartRepository.findByUserId(cartDTO.getUserid());
                    Product product = productRepository.findById(productsDTO.getProductid()).orElseThrow();

                    try {
                        Products products = productsRepository.findByProductidAndCart(product, cart);

                        Products updatedProduct = Products.builder()
                                .quantity(productsDTO.getQuantity())
                                .psid(products.getPsid())
                                .productid(product)
                                .cart(cart)
                                .build();

                        return updatedProduct;
                    } catch (NullPointerException e) {

                        Products products = Products.builder()
                                .cart(cart)
                                .productid(product)
                                .quantity(productsDTO.getQuantity())
                                .build();

                        return products;
                    }
                }
        ).collect(Collectors.toList());

        productsRepository.saveAll(productList);

        Cart updateCart = cartRepository.findByUserId(cartDTO.getUserid());

        log.info(updateCart);

        CartDTO dto = modelMapper.map(updateCart, CartDTO.class);

        log.info(dto);

        return dto;
    }

    @Override
    public CartDTO deleteCart(Long cid) {
        CartDTO dto = modelMapper.map(cartRepository.findById(cid).orElseThrow(), CartDTO.class);
        cartRepository.deleteById(cid);
        return dto;
    }
}
