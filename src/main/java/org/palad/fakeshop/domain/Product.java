package org.palad.fakeshop.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String title;

    private Long price;

    private String description;

    private String category;

    private String image;

    private Double rate;

    private Long count;


}
