package org.palad.fakeshop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long uid;

    private String email;

    private String username;

    private String password;

    private LocalDate joindate;

    private String phone;

    private NameDTO name;

    private AddressDTO address;

}
