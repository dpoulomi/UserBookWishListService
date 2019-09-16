package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.UserWishList;

import java.util.List;

public interface UserWishListDao {

    void addBookToWishList(UserWishList userWishList);

    void removeBookFromWishList(UserWishList userWishList);

    List<UserWishList> getUserWishList(int userId);

    List<UserWishList> getUserWishListByBook(int bookId);
}
