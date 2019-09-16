package com.home.userbookwishlist.externalobject;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@XmlRootElement(name = "ExternalUserWishList")
@NoArgsConstructor
public class ExternalUserWishList {

    private int userId;
    private int bookId;

    public ExternalUserWishList(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
