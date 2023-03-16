package org.palad.fakeshop.domain.product;

import javax.persistence.Embeddable;

@Embeddable
public class Rating {

    private Double rate;
    private Long count;

}
