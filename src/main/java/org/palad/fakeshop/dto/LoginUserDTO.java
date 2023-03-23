package org.palad.fakeshop.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;


@Getter
@Setter
@ToString
public class LoginUserDTO extends User {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]{10,20}$", message = "아이디는 10자 이상, 20자 이하여야 합니다.")
    private String username;

    @NotEmpty
    private String password;


    public LoginUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
    }
}
