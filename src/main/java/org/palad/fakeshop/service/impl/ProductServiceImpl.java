package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.domain.product.Category;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.dto.product.ProductDTO;

import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
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

    @Override
    public List<ProductDTO> getProductsWithSort(Sort sort) {
        Pageable pageable = PageRequest.of(0, 10, sort);

        List<Product> list = productRepository.findAll(pageable).toList();

        List<ProductDTO> dtoList = list.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<String> getCategories() {
        return Category.getList();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {

        List<Product> list = productRepository.getProductsByCategory(category);

        List<ProductDTO> dtoList = list.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product addedProduct = productRepository.save(product);
        ProductDTO dto = modelMapper.map(addedProduct, ProductDTO.class);
        return dto;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        log.info(product);
        Product updated = productRepository.save(product);
        ProductDTO dto = modelMapper.map(updated, ProductDTO.class);
        log.info(dto);
        return dto;
    }

    @Override
    public void deleteProduct(Long pid) {
        productRepository.deleteById(pid);
    }
}
