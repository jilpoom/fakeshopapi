package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getList();

    ProductDTO getProductById(Long pid);

    List<ProductDTO> getProductsWithLimit(int limit);

}
