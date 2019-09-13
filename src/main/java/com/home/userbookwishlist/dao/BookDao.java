package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.Book;

public interface BookDao {

    void addBook(final Book book);

    void removeBook(final int bookId);
}
