package org.palad.fakeshop.domain.product;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.palad.fakeshop.domain.cart.Cart;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotNull
    private String title;

    @NotNull
    private Long price;

    private String description;

    @NotNull
    private String category;

    private String image;

    @Embedded
    private Rating rating;



}
