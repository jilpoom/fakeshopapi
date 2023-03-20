package org.palad.fakeshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pid;

    @NotBlank
    private String title;

    @NotNull
    private Long price;

    private String description;

    @NotBlank
    private String category;

    private String image;

    private RatingDTO rating;

}
