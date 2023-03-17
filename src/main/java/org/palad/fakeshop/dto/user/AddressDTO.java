package org.palad.fakeshop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String city;

    private String zipcode;

    private Double longitude;

    private Double latitude;

}
