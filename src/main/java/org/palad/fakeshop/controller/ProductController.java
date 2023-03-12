package org.palad.fakeshop.controller;

import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.palad.fakeshop.dto.ProductDTO;
import org.palad.fakeshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDTO> getProducts(@RequestParam(required = false) String limit) {

        if(limit != null) {
            return productService.getProductsWithLimit(Integer.parseInt(limit));
        }

        return productService.getList();
    }

    @GetMapping("/{pid}")
    public ProductDTO getProduct(@PathVariable Long pid) {
        return productService.getProductById(pid);
    }

}
