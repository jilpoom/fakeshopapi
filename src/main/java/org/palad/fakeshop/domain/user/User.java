package org.palad.fakeshop.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.collection.internal.PersistentList;
import org.palad.fakeshop.domain.Product;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String email;

    private String username;

    private String password;

    private LocalDate joindate;

    private String firstname;

    private String lastname;

    private String phone;

    private String city;

    private String zipcode;

    private Double longitude;

    private Double latitude;



}
