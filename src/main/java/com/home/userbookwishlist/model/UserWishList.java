package com.home.userbookwishlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserWishList {

    /**
     *  FOREIGN KEY
     */
    private final int  userId;

    /**
     *  FOREIGN KEY
     */
    private final int bookId;

    public UserWishList(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
