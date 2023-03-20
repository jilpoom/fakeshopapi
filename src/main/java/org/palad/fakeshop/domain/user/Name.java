package org.palad.fakeshop.domain.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Name {

    private String firstname;

    private String lastname;

}
