package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.Book;

import java.util.List;

public interface UserWishListDao {

    void addBookToWishList(int userId, int bookId);

    void removeBookFromWishList(int userId, int bookId);

    List<Book> getUserWishList(int userId);
}
