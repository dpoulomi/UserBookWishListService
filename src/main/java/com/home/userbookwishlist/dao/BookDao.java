package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;

import java.util.List;

public interface BookDao {

    void addBook(final Book book);

    List<Book> getBook(final int bookId);

    void removeBook(final int bookId);
}
