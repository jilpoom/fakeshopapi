package org.palad.fakeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long cid;

    private Long userid;

    private LocalDate date;

    private Long productid;

    private Long quantity;

    @Builder.Default
    private Map<String, Long> products = new HashMap<>();

    public void addProducts(Long productid, Long quantity) {
        products.put("productid", productid);
        products.put("quantity", quantity);
    }


}
