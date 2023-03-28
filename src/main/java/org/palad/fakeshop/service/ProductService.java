package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.product.ProductDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getList();

    ProductDTO getProductById(String pid);

    /**
     * 23-03-27 14:50 Deprecated : <br>
     * getProductsWithLimitAndSort 메소드 사용 권장
     * @param limit
     * @return List
     */
    @Deprecated
    List<ProductDTO> getProductsWithLimit(int limit);

    /**
     * 23-03-27 14:23 Deprecated : <br>
     * getProductsWithLimitAndSort 메소드 사용 권장
     * @param sort
     * @return List
     */
    @Deprecated
    List<ProductDTO> getProductsWithSort(Sort sort);

    List<ProductDTO> getProductsWithLimitAndSort(String limit, String sort);

    List<String> getCategories();

    List<ProductDTO> getProductsByCategory(String category);

    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProduct(String pid);
}
