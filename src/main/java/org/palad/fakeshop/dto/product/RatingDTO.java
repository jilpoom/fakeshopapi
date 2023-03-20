package org.palad.fakeshop.dto.product;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private Double rate;
    private Long count;


}
