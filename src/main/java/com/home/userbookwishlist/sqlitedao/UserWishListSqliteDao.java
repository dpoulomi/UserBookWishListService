package com.home.userbookwishlist.sqlitedao;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.jdbc.UserWishListJdbcHelper;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.UserWishList;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Log4j
public class UserWishListSqliteDao implements UserWishListDao {

    private final UserWishListJdbcHelper userWishListJdbcHelper;

    @Inject
    public UserWishListSqliteDao(final UserWishListJdbcHelper userWishListJdbcHelper) {
        this.userWishListJdbcHelper = userWishListJdbcHelper;
    }

    @Override
    public void addBookToWishList(final UserWishList userWishList) {
        try {
            userWishListJdbcHelper.addBookToUserWishList(userWishList);
        } catch (SQLException ex) {
            log.error("Database call failed adding books to users wishlist", ex);
            throw new InternalServiceError("There are some internal errors adding your book to wishlist. Please try again.");
        }
    }

    @Override
    public void removeBookFromWishList(UserWishList userWishList) {
        try {
            userWishListJdbcHelper.removeBookFromWishList(userWishList);
        } catch (SQLException ex) {
            log.error("Error occured while removing userWishList");
            throw new InternalServiceError("Could not remove user wishlist. Please try again after sometime");
        }
    }

    @Override
    public List<UserWishList> getUserWishList(final int userId) {
        try {
            return userWishListJdbcHelper.getUserWishList(userId);
        } catch (SQLException e) {
            log.error("Error occured while getting userWishList");
            throw new InternalServiceError("Could not get UserWishList at this moment.");
        }
    }

    @Override
    public List<UserWishList> getUserWishListByBook(final int bookId) {
        try {
            return userWishListJdbcHelper.getUserWishListByBook(bookId);
        } catch (SQLException e) {
            log.error("Error occured while getting userWishList");
            throw new InternalServiceError("Could not get UserWishList at this moment.");
        }
    }
}
