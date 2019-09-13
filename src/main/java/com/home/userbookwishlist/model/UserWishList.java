package com.home.userbookwishlist.model;

public class UserWishList {


    private final int  userId;

    private final int bookId;

    public UserWishList(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }
}
