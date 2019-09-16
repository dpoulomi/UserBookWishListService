package com.home.userbookwishlist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Builder
@AllArgsConstructor
public class User {

    /**
     *  PRIMARY KEY
     */
    private int userId;

    private String firstName;
    private String lastName;
    private String email;
}
