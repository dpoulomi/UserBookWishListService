package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserJdbcHelper {

    private final static String ADD_USER_STATEMENT =
            "INSERT INTO User (user_id, first_name, last_name, email, password) VALUES(?,?,?,?,?)";

    private final static String DELETE_USER_STATEMENT =
            "delete from User where user_id = ?";

    private final Connection sqliteConnection;

    public UserJdbcHelper(final Connection connection) {
        this.sqliteConnection = checkNotNull(connection);
    }

    public void addUser(final User user) throws SQLException {
        final PreparedStatement addBookPrepatedStatement = sqliteConnection.prepareStatement(ADD_USER_STATEMENT);
        try  {
            addBookPrepatedStatement.setInt(1 , user.getUserId());
            addBookPrepatedStatement.setString(2, user.getFirstName());
            addBookPrepatedStatement.setString(3, user.getLastName());
            addBookPrepatedStatement.setString(4, user.getEmail());
            addBookPrepatedStatement.setString(5, user.getPassword());
            addBookPrepatedStatement.executeUpdate();
        } finally {
            addBookPrepatedStatement.close();
        }
    }

    public void removeUser(final int userId) throws SQLException {
        final PreparedStatement deleteBookPrepatedStatement = sqliteConnection.prepareStatement(DELETE_USER_STATEMENT);
        try  {
            deleteBookPrepatedStatement.setInt(1 , userId);
            deleteBookPrepatedStatement.executeUpdate();
        } finally {
            deleteBookPrepatedStatement.close();
        }
    }
}
