package com.home.userbookwishlist.model;

import lombok.Builder;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Builder
public class User {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final int userId;

    public User(final String firstName,
                final String last_name,
                final String email,
                final String password,
                final int userId) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }
}
