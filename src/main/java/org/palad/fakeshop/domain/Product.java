package org.palad.fakeshop.domain;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
@ToString
@DynamicUpdate
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

    private Double rate;

    private Long count;


}
