package org.palad.fakeshop.domain.cart;

import lombok.*;
import org.palad.fakeshop.domain.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "제품 아이디를 반드시 입력해야 합니다 : productid")
    private Product productid;

    @NotNull(message = "제품의 개수를 반드시 입력해야 합니다 : quantity"  )
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


}
