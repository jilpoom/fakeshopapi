package org.palad.fakeshop.domain.product;


import com.sun.istack.NotNull;
import lombok.*;

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
