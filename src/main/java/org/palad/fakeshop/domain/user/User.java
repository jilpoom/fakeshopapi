package org.palad.fakeshop.domain.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String email;

    private String username;

    private String password;

    private LocalDate joindate;

    @Embedded
    private Name name;

    private String phone;

    @Embedded
    private Address address;

}
