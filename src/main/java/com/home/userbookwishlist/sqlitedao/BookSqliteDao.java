package com.home.userbookwishlist.sqlitedao;

import com.google.inject.Inject;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookSqliteDao implements BookDao {

    private final BookJdbcHelper bookJdbcHelper;

    @Inject
    public BookSqliteDao(final BookJdbcHelper addBookJdbcHelper) {
        this.bookJdbcHelper = addBookJdbcHelper;
    }

    @Override
    public void addBook(final Book book) {
        try {
            bookJdbcHelper.addBook(book);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Add Book api is down for sometime. Please try again later!");
        }
    }

    @Override
    public List<Book> getBook(final int bookId) {
        try {
            return bookJdbcHelper.getBook(bookId);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Add Book api is down for sometime. Please try again later!");
        }
    }

    @Override
    public void removeBook(final int bookId) {
        try {
            bookJdbcHelper.removeBook(bookId);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Add Book api is down for sometime. Please try again later!");
        }
    }
}
