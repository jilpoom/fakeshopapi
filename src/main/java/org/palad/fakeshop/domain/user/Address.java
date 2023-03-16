package org.palad.fakeshop.domain.user;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;

    private String zipcode;

    private Double longitude;

    private Double latitude;



}
