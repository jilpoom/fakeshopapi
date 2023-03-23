package org.palad.fakeshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private Long uid;

    private String email;

    private String username;

    private String password;
    private Collection<GrantedAuthority> authorities;

    private String joindate;

    private String phone;

    private NameDTO name;

    private AddressDTO address;


}
