package com.home.userbookwishlist.sqlitedao;

import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.model.Book;

import java.util.List;

public class UserWishListSqliteDao implements UserWishListDao {


    @Override
    public void addBookToWishList(final int userId, final int bookId) {

    }

    @Override
    public void removeBookFromWishList(final int userId, final int bookId) {

    }

    @Override
    public List<Book> getUserWishList(final int userId) {
        return null;
    }
}
