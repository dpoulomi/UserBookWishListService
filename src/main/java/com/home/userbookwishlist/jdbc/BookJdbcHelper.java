package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.constants.Constants;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookJdbcHelper {

    private final static String ADD_BOOK_STATEMENT =
            String.format("insert into %s (%s, %s, %s, %s, %s) VALUES(?,?,?,?,?)",
                    Constants.TableName.BOOKS_TABLE,
                    Constants.BooksTableAttributeName.ISBN,
                    Constants.BooksTableAttributeName.TITLE,
                    Constants.BooksTableAttributeName.AUTHOR,
                    Constants.BooksTableAttributeName.DATE_OF_PUBLICATION,
                    Constants.BooksTableAttributeName.BOOK_ID);

    private final static String GET_BOOK_STATEMENT =
            String.format("select * from %s where %s = ?",
                    Constants.TableName.BOOKS_TABLE,
                    Constants.BooksTableAttributeName.BOOK_ID);

    private final static String DELETE_BOOK_STATEMENT =
            String.format("delete from %s where %s = ?",
                    Constants.TableName.BOOKS_TABLE,
                    Constants.BooksTableAttributeName.BOOK_ID);

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

    public List<Book> getBook(final int bookId) throws SQLException {
        final List<Book> result = new ArrayList<>();
        final PreparedStatement addBookPrepatedStatement = sqliteConnection.prepareStatement(GET_BOOK_STATEMENT);
        try  {
            addBookPrepatedStatement.setInt(1 , bookId);
            ResultSet resultSet = addBookPrepatedStatement.executeQuery();
            while (resultSet.next()) {
                final int resultBookId = resultSet.getInt("book_id");
                final int resultIsbn = resultSet.getInt("isbn");
                final String resultTitle = resultSet.getString("title");
                final String resultAuthor = resultSet.getString("author");
                final String resultDateOfPublication = resultSet.getString("date_of_publication");

                result.add(Book.builder()
                        .bookId(resultBookId)
                        .isbn(resultIsbn)
                        .title(resultTitle)
                        .author(resultAuthor)
                        .dateOfPublication(resultDateOfPublication)
                        .build());
            }
        } finally {
            addBookPrepatedStatement.close();
        }
        return result;
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
