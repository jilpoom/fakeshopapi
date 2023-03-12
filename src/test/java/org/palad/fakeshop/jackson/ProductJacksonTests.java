package org.palad.fakeshop.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.Product;
import org.palad.fakeshop.infra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ProductJacksonTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Product Entity To Json")
    public void productToJson() throws JsonProcessingException {

        Product product = productRepository.findById(1L).orElseThrow();

        String productJson = objectMapper.writeValueAsString(product);

        log.info("--------------------------");
        log.info(productJson);


    }
}
