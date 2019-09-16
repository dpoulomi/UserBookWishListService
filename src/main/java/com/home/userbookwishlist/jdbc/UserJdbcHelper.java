package com.home.userbookwishlist.jdbc;

import com.google.inject.Inject;
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

public class UserJdbcHelper {

    private final static String ADD_USER_STATEMENT =
            String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES(?,?,?,?)",
                    Constants.TableName.USERS_TABLE,
                    Constants.UsersTableAttributeName.USERS_ID,
                    Constants.UsersTableAttributeName.FIRST_NAME,
                    Constants.UsersTableAttributeName.LAST_NAME,
                    Constants.UsersTableAttributeName.EMAIL);

    private final static String GET_USER_STATEMENT =
            String.format("select * from %s where %s = ?",
                    Constants.TableName.USERS_TABLE,
                    Constants.UsersTableAttributeName.USERS_ID);

    private final static String DELETE_USER_STATEMENT =
            String.format("delete from %s where %s = ?",
                    Constants.TableName.USERS_TABLE,
                    Constants.UsersTableAttributeName.USERS_ID);

    private final Connection sqliteConnection;

    @Inject
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
            addBookPrepatedStatement.executeUpdate();
        } finally {
            addBookPrepatedStatement.close();
        }
    }

    public List<User> getUser(final int userId) throws SQLException {
        final List<User> result = new ArrayList<>();
        final PreparedStatement addBookPrepatedStatement = sqliteConnection.prepareStatement(GET_USER_STATEMENT);
        try  {
            addBookPrepatedStatement.setInt(1 , userId);
            ResultSet resultSet = addBookPrepatedStatement.executeQuery();
            while (resultSet.next()) {
                final int resultUserId = resultSet.getInt("user_id");
                final String resultFirstName = resultSet.getString("first_name");
                final String resultLastName = resultSet.getString("last_name");
                final String resultEmail = resultSet.getString("email");

                result.add(User.builder()
                        .userId(resultUserId)
                        .firstName(resultFirstName)
                        .lastName(resultLastName)
                        .email(resultEmail)
                        .build());
            }
        } finally {
            addBookPrepatedStatement.close();
        }
        return result;
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
