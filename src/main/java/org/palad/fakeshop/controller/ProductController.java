package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.controller.exception.BindingResultMapper;
import org.palad.fakeshop.dto.product.ProductDTO;
import org.palad.fakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
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
    private final BindingResultMapper bindingResultMapper;
    @Value("${fakeshop.upload.path}")
    private String uploadPath;


    @GetMapping()
    public List<ProductDTO> getProducts(@RequestParam(required = false) String limit,
                                        @RequestParam(required = false, defaultValue = "asc") String sort){

        if(limit != null || !sort.equals("asc")) {
            return productService.getProductsWithLimitAndSort(limit, sort);
        }
        return productService.getList();
    }

    @GetMapping("/{pid}")
    public ProductDTO getProduct(@PathVariable String pid) {
        return productService.getProductById(pid);
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getCategories();
    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> getCategory(@PathVariable String category) throws IllegalArgumentException{
        return productService.getProductsByCategory(category);
    }

    @PostMapping
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO,
                                 BindingResult bindingResult) throws BindException{

        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        return productService.addProduct(productDTO);
    }



    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        productService.updateProduct(productDTO);
        return productDTO;
    }

    @DeleteMapping("/{pid}")
    public ProductDTO deleteProduct(@PathVariable String pid) {
        log.info("DELETE : " + pid);
        ProductDTO dto = productService.getProductById(pid);
        productService.deleteProduct(pid);
        return dto;
    }
}
