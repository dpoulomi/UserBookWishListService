package com.home.userbookwishlist.jdbc;

import java.sql.Connection;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserWishListJdbcHelper {

    private final static String ADD_USER_WISH_LIST_STATEMENT =
            "INSERT INTO User (user_id, book_id) VALUES(?,?)";

    private final static String DELETE_USER_STATEMENT =
            "delete from User where user_id = ?";

    private final Connection sqliteConnection;

    public UserWishListJdbcHelper(final Connection connection) {
        this.sqliteConnection = checkNotNull(connection);
    }

    public void addBookToUserWishList(final int userId, final int bookId) {

    }
}
