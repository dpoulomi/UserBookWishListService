package com.home.userbookwishlist.service;

import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.sqlitedao.UserWishListSqliteDao;

public class UserWishListService {

    private final UserWishListDao userWishListDao;

    public UserWishListService(final UserWishListDao userWishListDao) {
        this.userWishListDao = userWishListDao;
    }

    public void addBookToUserWishList(final int userId, final int bookId) {
        userWishListDao.addBookToWishList(userId, bookId);
    }
}
