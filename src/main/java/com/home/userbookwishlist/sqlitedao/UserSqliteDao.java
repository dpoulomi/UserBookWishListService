package com.home.userbookwishlist.sqlitedao;

import com.google.inject.Inject;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.jdbc.UserJdbcHelper;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserSqliteDao implements UserDao {

    private final UserJdbcHelper userJdbcHelper;

    @Inject
    public UserSqliteDao(final UserJdbcHelper userJdbcHelper) {
        this.userJdbcHelper = userJdbcHelper;
    }

    @Override
    public void addUser(final User user) {
        try {
            userJdbcHelper.addUser(user);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Add Book api is down for sometime. Please try again later!");
        }
    }

    @Override
    public List<User> getUser(final int userId) {
        try {
            return userJdbcHelper.getUser(userId);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Add Book api is down for sometime. Please try again later!");
        }
    }


    @Override
    public void removeUser(final int userId) {
        try {
            userJdbcHelper.removeUser(userId);
        } catch(final SQLException ex) {
            throw new InternalServiceError("Remove User api is down for sometime. Please try again later!");
        }
    }
}
