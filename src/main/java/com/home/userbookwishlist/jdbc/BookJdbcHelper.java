package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookJdbcHelper {

    private final static String ADD_BOOK_STATEMENT =
            "INSERT INTO Book (isbn, title, author, date_of_publication, book_id) VALUES(?,?,?,?,?)";

    private final static String DELETE_BOOK_STATEMENT =
            "delete from Book where book_id = ?";

    private final Connection sqliteConnection;

    public BookJdbcHelper(final Connection connection) {
        this.sqliteConnection = checkNotNull(connection);
    }

    public void addBook(final Book book) throws SQLException {
        final PreparedStatement addBookPrepatedStatement = sqliteConnection.prepareStatement(ADD_BOOK_STATEMENT);
        try  {
            addBookPrepatedStatement.setInt(1 , book.getIsbn());
            addBookPrepatedStatement.setString(2, book.getTitle());
            addBookPrepatedStatement.setString(3, book.getAuthor());
            addBookPrepatedStatement.setString(4, book.getDateOfPublication());
            addBookPrepatedStatement.setInt(5, book.getBookId());
            addBookPrepatedStatement.executeUpdate();
        } finally {
            addBookPrepatedStatement.close();
        }
    }

    public void removeBook(final int bookId) throws SQLException {
        final PreparedStatement deleteBookPrepatedStatement = sqliteConnection.prepareStatement(DELETE_BOOK_STATEMENT);
        try  {
            deleteBookPrepatedStatement.setInt(1 , bookId);
            deleteBookPrepatedStatement.executeUpdate();
        } finally {
            deleteBookPrepatedStatement.close();
        }
    }
}
