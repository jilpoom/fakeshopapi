package org.palad.fakeshop.domain.cart;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psid;

    private Long productid;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

}
