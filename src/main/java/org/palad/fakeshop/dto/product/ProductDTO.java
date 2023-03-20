package org.palad.fakeshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pid;

    @NotBlank
    private String title;

    @NotBlank
    private Long price;

    private String description;

    @NotBlank
    private String category;

    private String image;


    private RatingDTO rating;

}
