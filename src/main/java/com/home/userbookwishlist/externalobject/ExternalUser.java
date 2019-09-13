package com.home.userbookwishlist.externalobject;

import lombok.Builder;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Builder
@XmlRootElement
public class ExternalUser {

    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public ExternalUser(final int userId,
                        final String firstName,
                        final String lastName,
                        final String email,
                        final String password) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
