package org.palad.fakeshop.dto;

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

    private String firstname;

    private String lastname;

    private String phone;

    public Map<String, String> getName() {
        Map<String, String> map = new HashMap<>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        return map;
    }

}
