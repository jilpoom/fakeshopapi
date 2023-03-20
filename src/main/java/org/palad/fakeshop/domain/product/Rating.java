package org.palad.fakeshop.domain.product;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {

    private Double rate;
    private Long count;

}
