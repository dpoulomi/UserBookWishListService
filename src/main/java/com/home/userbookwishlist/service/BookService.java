package com.home.userbookwishlist.service;

import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.model.Book;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookService {

    private final BookDao bookDao;

    public BookService(final BookDao bookDao) {
        this.bookDao = checkNotNull(bookDao);
    }

    public void addBook(final ExternalBook externalBook) {
        final Book book = convertToBook(externalBook);
        bookDao.addBook(book);
    }

    public void removeBook(final int bookId) {
        bookDao.removeBook(bookId);
    }


    private Book convertToBook(final ExternalBook externalBook) {
        return Book.builder()
                .author(externalBook.getAuthor())
                .bookId(externalBook.getBookId())
                .dateOfPublication(externalBook.getDateOfPublication())
                .isbn(externalBook.getIsbn())
                .title(externalBook.getTitle())
                .build();
    }

}