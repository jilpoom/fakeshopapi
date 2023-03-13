package org.palad.fakeshop.dto;

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

    @JsonIgnore
    private String firstname;

    @JsonIgnore
    private String lastname;

    private String phone;

    public Map<String, String> getName() {
        Map<String, String> map = new HashMap<>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        return map;
    }

    @JsonIgnore
    private String city;

    @JsonIgnore
    private String zipcode;

    @JsonIgnore
    private Double longitude;

    @JsonIgnore
    private Double latitude;

    public Map<String, Object> getAddress() {
        Map<String, Object> addressMap = new HashMap<>();
        addressMap.put("address", city);
        addressMap.put("zipcode", zipcode);
        addressMap.put("longitude", longitude);
        addressMap.put("latitude", latitude);
        return addressMap;
    }
}
