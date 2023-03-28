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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<CartDTO> getCartsWithLimitAndSort(String limit, String sort) {

        int size = Long.valueOf(cartRepository.count()).intValue();
        Sort orders = null;

        if(sort != null) {
            if (!(sort.equals("asc") || sort.equals("desc"))) {
                throw new IllegalArgumentException("sort의 값은 'desc', 'asc' 외엔 입력할 수 없습니다.");
            } else {
                if(sort.equals("desc")) {
                    orders = Sort.by("cid").descending();
                } else {
                    orders = Sort.by("cid").ascending();
                }
            }
        }

        if(limit != null) {
            if (!limit.matches("\\d+")) {
                throw new IllegalArgumentException("limit의 값은 숫자만 입력해주세요");
            } else {
                size = Integer.parseInt(limit);
            }
        }

        Pageable pageable = PageRequest.of(0, size, orders);

        List<Cart> cartGroup = cartRepository.findAll(pageable).toList();

        List<CartDTO> cartDTOGroup = cartGroup.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return cartDTOGroup;
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

    @Override
    public List<CartDTO> getCartsByDate(String startDate, String endDate) {
        List<Cart> cartGroup = cartRepository.findByDateBetween(StringToDate(startDate), StringToDate(endDate))
                .orElseThrow(() -> new IllegalArgumentException("날짜 형식이 정확하지 않습니다."));

        List<CartDTO> cartDTOGroup = cartGroup.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return cartDTOGroup;
    }

    private LocalDate StringToDate(String date) {
        String[] dateParts = date.split("-");
        return LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
    }
}
