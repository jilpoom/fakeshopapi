package org.palad.fakeshop.domain.cart;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.service.ProductService;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cid;

    @OneToOne
    private User user;

    private LocalDate date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Products> products = new ArrayList<>();

    public void addProducts(Products products) {

        for(int i = 0; i < this.products.size(); i++) {
            if(this.products.get(i).getProductid() == products.getProductid()) {
                this.products.remove(i);
            }
        }

        this.products.add(products);
    }


}
