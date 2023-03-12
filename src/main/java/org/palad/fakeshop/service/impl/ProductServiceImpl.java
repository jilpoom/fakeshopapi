package org.palad.fakeshop.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.domain.Product;
import org.palad.fakeshop.dto.ProductDTO;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<ProductDTO> getList() {
        List<Product> list = productRepository.findAll();

        List<ProductDTO> dtoList = list.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public ProductDTO getProductById(Long pid) {
        Product product = productRepository.findById(pid).orElseThrow();
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        return dto;
    }

    @Override
    public List<ProductDTO> getProductsWithLimit(int limit) {
        Pageable pageable = PageRequest.of(0, limit);

        List<Product> list = productRepository.findAll(pageable).toList();

        List<ProductDTO> dtoList = list.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
}
