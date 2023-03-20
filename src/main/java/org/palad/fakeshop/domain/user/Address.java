package org.palad.fakeshop.domain.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private String city;

    private String zipcode;

    private Double longitude;

    private Double latitude;



}
