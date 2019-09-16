package com.home.userbookwishlist.service;

import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.UserWishList;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookService {

    private final UserWishListDao userWishListDao;
    private final BookDao bookDao;

    public BookService(final UserWishListDao userWishListDao, final BookDao bookDao) {
        this.userWishListDao = userWishListDao;
        this.bookDao = checkNotNull(bookDao);
    }

    public String addBook(final ExternalBook externalBook) {
        final Book book = convertToBook(externalBook);
        if(isBookNotExist(book.getBookId())) {
            bookDao.addBook(book);
            return String.format("book_id : %d has been added successfully", book.getBookId());
        }
        return String.format("book_id : %d already exists.", book.getBookId());
    }

    public String getBook(final int bookId) {
        List<Book> bookList = bookDao.getBook(bookId);
        return toHtml(bookList);
    }

    public String removeBook(final int bookId) {
        if(isBookNotExist(bookId)) {
            return String.format("book_id %d doesn't exist in our system.", bookId);
        }

        List<UserWishList> userWishListResult = userWishListDao.getUserWishListByBook(bookId);
        if(!CollectionUtils.isEmpty(userWishListResult)) {
            userWishListResult.stream()
                    .forEach(userWishList -> userWishListDao.removeBookFromWishList(userWishList));
        }
        bookDao.removeBook(bookId);
        return String.format("book_id %d has been removed successfully.", bookId);
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

    private boolean isBookNotExist(final int bookId) {
        return bookDao.getBook(bookId).size() == 0;
    }

    private String toHtml(final List<Book> bookList) {
        StringBuilder html = new StringBuilder();
        bookList.stream()
                .forEach(book -> html.append("<h2>")
                                     .append(book.getTitle())
                                     .append("</h2>"));
        return html.toString();
    }
}