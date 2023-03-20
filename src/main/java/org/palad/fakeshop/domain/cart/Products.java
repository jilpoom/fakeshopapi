package org.palad.fakeshop.domain.cart;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.palad.fakeshop.domain.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cart"})
@Builder
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psid;

    @OneToOne
    private Product productid;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


}
