package com.home.userbookwishlist.jdbc;

import com.google.inject.Inject;
import com.home.userbookwishlist.constants.Constants;
import com.home.userbookwishlist.model.UserWishList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserWishListJdbcHelper {

    private final static String ADD_USER_WISH_LIST_STATEMENT =
            String.format("INSERT INTO %s (%s, %s) VALUES(?,?)",
                    Constants.TableName.USER_WISH_LIST_TABLE,
                    Constants.UserWishListTableAttributeName.USER_ID,
                    Constants.UserWishListTableAttributeName.BOOK_ID);

    private final static String GET_USER_WISH_LIST_STATEMENT =
            String.format("select * from %s where %s = ?",
                    Constants.TableName.USER_WISH_LIST_TABLE,
                    Constants.UserWishListTableAttributeName.USER_ID);

    private final static String GET_USER_WISH_LIST_BY_BOOK_STATEMENT =
            String.format("select * from %s where %s = ?",
                    Constants.TableName.USER_WISH_LIST_TABLE,
                    Constants.UserWishListTableAttributeName.BOOK_ID);

    private final static String DELETE_USER_STATEMENT =
            String.format("delete from %s where %s = ? and %s = ?",
                    Constants.TableName.USER_WISH_LIST_TABLE,
                    Constants.UserWishListTableAttributeName.USER_ID,
                    Constants.UserWishListTableAttributeName.BOOK_ID);

    private final Connection sqliteConnection;

    @Inject
    public UserWishListJdbcHelper(final Connection connection) {
        this.sqliteConnection = checkNotNull(connection);
    }

    public void addBookToUserWishList(final UserWishList userWishList) throws SQLException {
        final PreparedStatement addBookPrepatedStatement = sqliteConnection.prepareStatement(ADD_USER_WISH_LIST_STATEMENT);
        try {
            addBookPrepatedStatement.setInt(1, userWishList.getUserId());
            addBookPrepatedStatement.setInt(2, userWishList.getBookId());
            addBookPrepatedStatement.executeUpdate();
        } finally {
            addBookPrepatedStatement.close();
        }
    }

    public List<UserWishList> getUserWishList(final int userId) throws SQLException {
        final List<UserWishList> result = new ArrayList<>();
        final PreparedStatement getBookPrepatedStatement = sqliteConnection.prepareStatement(GET_USER_WISH_LIST_STATEMENT);
        try {
            getBookPrepatedStatement.setInt(1, userId);
            final ResultSet resultSet = getBookPrepatedStatement.executeQuery();
            while (resultSet.next()) {
                final int resultUserId = resultSet.getInt(Constants.UserWishListTableAttributeName.USER_ID);
                final int resultBookId = resultSet.getInt(Constants.UserWishListTableAttributeName.BOOK_ID);
                result.add(UserWishList.builder()
                        .userId(resultUserId)
                        .bookId(resultBookId)
                        .build());
            }
        } finally {
            getBookPrepatedStatement.close();
        }
        return result;
    }

    public List<UserWishList> getUserWishListByBook(final int bookId) throws SQLException {
        final List<UserWishList> result = new ArrayList<>();
        final PreparedStatement getBookPrepatedStatement =
                sqliteConnection.prepareStatement(GET_USER_WISH_LIST_BY_BOOK_STATEMENT);
        try {
            getBookPrepatedStatement.setInt(1, bookId);
            final ResultSet resultSet = getBookPrepatedStatement.executeQuery();
            while (resultSet.next()) {
                final int resultUserId = resultSet.getInt(Constants.UserWishListTableAttributeName.USER_ID);
                final int resultBookId = resultSet.getInt(Constants.UserWishListTableAttributeName.BOOK_ID);
                result.add(UserWishList.builder()
                        .userId(resultUserId)
                        .bookId(resultBookId)
                        .build());
            }
        } finally {
            getBookPrepatedStatement.close();
        }
        return result;
    }

    public void removeBookFromWishList(final UserWishList userWishList) throws SQLException {
        final PreparedStatement removeBookFromUserWishListPrepatedStatement = sqliteConnection.prepareStatement(DELETE_USER_STATEMENT);
        try {
            removeBookFromUserWishListPrepatedStatement.setInt(1, userWishList.getUserId());
            removeBookFromUserWishListPrepatedStatement.setInt(2, userWishList.getBookId());
            removeBookFromUserWishListPrepatedStatement.executeUpdate();
        } finally {
            removeBookFromUserWishListPrepatedStatement.close();
        }
    }
}
