package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.domain.Category;
import org.palad.fakeshop.domain.Product;
import org.palad.fakeshop.dto.ProductDTO;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Product Insert Tests")
    public void productInsert() {

        Product product = Product.builder()
                .category(Category.MAN_CLOTHING.name())
                .description("man's clothing description")
                .title("shirt")
                .image("shirt.jpg")
                .price(10000L)
                .rate(3.2)
                .count(100L)
                .build();


        productRepository.save(product);

    }

    @Test
    @DisplayName("Products All Insert")
    public void allProductInsert() {

        for(int i = 0; i < 100; i++) {
            String category = "";

            if(i % 5 == 0) {
                category = Category.MAN_CLOTHING.getValue();
            } else if (i % 5 == 1) {
                category = Category.WOMAN_CLOTHING.getValue();
            } else if (i % 5 == 2) {
                category = Category.BACKPACK.getValue();
            } else if (i % 5 == 3) {
                category = Category.CAP.getValue();
            } else {
                category = Category.ETC.getValue();
            }

            Product product = Product.builder()
                    .title(category + i)
                    .category(category)
                    .count(Long.valueOf(i))
                    .description(category + " description")
                    .image(category + i + ".jpg")
                    .price(i * 1000L)
                    .rate((double) i % 10)
                    .build();

            productRepository.save(product);
        }

    }

    @Test
    @DisplayName("특정 카테고리별 제품 가져오기")
    public void allProductByCategory() {

        String category =  "mansclothing";

        String enumCategory = Category.getCategoryByValue(category);

        List<Product> list = productRepository.getProductsByCategory(enumCategory);

        list.forEach(product -> log.info(product));
    }

    @Test
    @DisplayName("PRODUCT INSERT")
    public void InsertProduct() {

        Product product = Product.builder()
                .title("NEW ITEM")
                .price(10000L)
                .description("NEW ITEM Description")
                .image("newitem.jpg")
                .build();

        productRepository.save(product);
    }

    @Test
    @DisplayName("PRODUCT UPDATE")
    public void updateProductTest() {

        Product product = productRepository.findById(3L).orElseThrow();

        log.info(product);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        log.info(productDTO);

        productDTO.setCategory("mansclothing");
        productDTO.setCount(1L);
        productDTO.setRate(5.0);

        Product updatedProduct = modelMapper.map(productDTO, Product.class);

        log.info(updatedProduct);

        productRepository.save(updatedProduct);


    }

}
