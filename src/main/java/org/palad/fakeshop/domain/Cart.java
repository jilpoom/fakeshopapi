package org.palad.fakeshop.domain;


import lombok.*;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.service.ProductService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @OneToOne
    private User user;

    private LocalDate date;

    private Long productid;

    private Long quantity;



}
