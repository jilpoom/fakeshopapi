package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.Category;
import org.palad.fakeshop.domain.Product;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.palad.fakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

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

            if(i % 4 == 0) {
                category = Category.MAN_CLOTHING.name();
            } else if (i % 4 == 1) {
                category = Category.WOMAN_CLOTHING.name();
            } else if (i % 4 == 2) {
                category = Category.BACKPACK.name();
            } else if (i % 4 == 3) {
                category = Category.CAP.name();
            }

            Product product = Product.builder()
                    .title(category + i)
                    .category(category)
                    .count(Long.valueOf(i))
                    .description(category + "description")
                    .image(category + i + ".jpg")
                    .price(i * 1000L)
                    .rate((double) i % 10)
                    .build();

            productRepository.save(product);
        }



    }


}
