package org.palad.fakeshop.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long cid;

    private Long userid;

    private String date;

    private List<ProductsDTO> products;


}
