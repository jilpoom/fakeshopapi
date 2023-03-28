package org.palad.fakeshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private Long uid;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일의 형식을 준수해주세요")
    private String email;

    @NotNull
    private String username;

    private String password;

    private Collection<GrantedAuthority> authorities;


    private String joindate;

    @Pattern(regexp = "^(01[016789]{1})-(\\d{3,4})-(\\d{4})$", message = "휴대폰 번호의 형식을 준수해주세요")
    private String phone;

    private NameDTO name;

    private AddressDTO address;


}
