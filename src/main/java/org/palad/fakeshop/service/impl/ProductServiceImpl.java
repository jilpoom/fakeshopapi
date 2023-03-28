package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.controller.exception.NotFoundException;
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
    public ProductDTO getProductById(String pid) {
        if (!pid.matches("\\d+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        Product product = productRepository.findById(Long.valueOf(pid)).orElseThrow(() -> new NotFoundException("해당 제품을 찾을 수 없습니다 : " + pid));
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
    public List<ProductDTO> getProductsWithLimitAndSort(String limit, String sort) {
       int size = Long.valueOf(productRepository.count()).intValue();
       Sort orders = null;

        if(sort != null) {
            if (!(sort.equals("asc") || sort.equals("desc"))) {
                throw new IllegalArgumentException("sort의 값은 'desc', 'asc' 외엔 입력할 수 없습니다.");
            } else {
                if(sort.equals("desc")) {
                    orders = Sort.by("pid").descending();
                } else {
                    orders = Sort.by("pid").ascending();
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

        List<Product> productGroup = productRepository.findAll(pageable).toList();

        List<ProductDTO> productDTOGroup = productGroup.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOGroup;

    }

    @Override
    public List<String> getCategories() {
        return Category.getList();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {

        if(!isCategory(category)) {
            throw new IllegalArgumentException("올바른 카테고리가 아닙니다.");
        }

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
        if(productDTO.getPid() == null) {
            throw new NotFoundException("pid 필드를 반드시 입력해야합니다.");
        }

        Product product = modelMapper.map(productDTO, Product.class);
        log.info(product);
        Product updated = productRepository.save(product);
        ProductDTO dto = modelMapper.map(updated, ProductDTO.class);
        log.info(dto);
        return dto;
    }

    @Override
    public void deleteProduct(String pid) {
        productRepository.deleteById(Long.valueOf(pid));
    }

    private boolean isCategory(String category) {
        return Category.getList().stream().anyMatch(type -> type.equals(category));
    }
}
