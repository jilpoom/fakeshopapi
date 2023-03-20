package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.product.ProductDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getList();

    ProductDTO getProductById(Long pid);

    List<ProductDTO> getProductsWithLimit(int limit);

    List<ProductDTO> getProductsWithSort(Sort sort);

    List<String> getCategories();

    List<ProductDTO> getProductsByCategory(String category);

    void addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProduct(Long pid);
}
