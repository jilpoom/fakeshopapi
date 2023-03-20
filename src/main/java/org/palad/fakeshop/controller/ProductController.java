package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.dto.product.ProductDTO;
import org.palad.fakeshop.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDTO> getProducts(@RequestParam(required = false) String limit,
                                        @RequestParam(required = false) String sort) {

        if (limit != null) {

            return productService.getProductsWithLimit(Integer.parseInt(limit));
        }

        if (sort != null) {
            Sort sorts = Sort.by("pid").descending();

            if (sort.equals("desc")) return productService.getProductsWithSort(sorts);
        }

        return productService.getList();
    }

    @GetMapping("/{pid}")
    public ProductDTO getProduct(@PathVariable String pid) {

        if (pid.matches("[A-Za-z](.*)")) {
            throw new NumberFormatException("unvalid variable");
        }


        return productService.getProductById(Long.valueOf(pid));
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getCategories();
    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> getCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping()
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        log.info(productDTO);

        if (bindingResult.hasErrors()) {

        }

        productService.addProduct(productDTO);

        return productDTO;
    }

    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        log.info(productDTO);
        productService.updateProduct(productDTO);
        return productDTO;
    }

    @DeleteMapping("/{pid}")
    public ProductDTO deleteProduct(@PathVariable Long pid) {
        log.info("DELETE : " + pid);
        ProductDTO dto = productService.getProductById(pid);
        productService.deleteProduct(pid);
        return dto;
    }
}
