package org.palad.fakeshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pid;

    @NotNull(message = "타이틀을 입력해주세요 : title" )
    private String title;

    @NotNull(message = "가격을 입력해주세요 : price")
    private Long price;

    private String description;

    @NotNull(message = "카테고리를 입력해주세요 : category")
    private String category;

    private String image;

    private RatingDTO rating;

}
