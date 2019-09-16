package com.home.userbookwishlist.externalobject;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@XmlRootElement(name = "ExternalUser")
@NoArgsConstructor
public class ExternalUser {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    public ExternalUser(final int userId,
                        final String firstName,
                        final String lastName,
                        final String email) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
