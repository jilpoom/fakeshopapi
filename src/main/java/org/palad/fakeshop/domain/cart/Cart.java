package org.palad.fakeshop.domain.cart;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.service.ProductService;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Products> products;

    public void addProducts(User user, List<Products> products) {
        this.products = products;
        this.user = user;
    }

    public void dateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localdate = LocalDate.parse(date, formatter);
        this.date = localdate;
    }


}
