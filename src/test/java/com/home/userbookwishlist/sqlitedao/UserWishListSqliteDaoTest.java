package com.home.userbookwishlist.sqlitedao;

import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalUserWishList;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.jdbc.UserWishListJdbcHelper;
import com.home.userbookwishlist.model.UserWishList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class UserWishListSqliteDaoTest {

    private static final UserWishList SAMPLE_EXTERNAL_USER_WISHLIST = UserWishList.builder()
            .userId(1)
            .bookId(1)
            .build();

    private UserWishListSqliteDao userWishListSqliteDao;

    @Mock
    private UserWishListJdbcHelper mockUserWishListJdbcHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userWishListSqliteDao = new UserWishListSqliteDao(mockUserWishListJdbcHelper);
    }

    @Test
    public void addBookToWishList_Success_ForValidInput() throws SQLException {
        userWishListSqliteDao.addBookToWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        verify(mockUserWishListJdbcHelper, times(1))
                .addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        verifyNoMoreInteractions(mockUserWishListJdbcHelper);
    }

    @Test
    public void removeBookToWishList_Success_ForValidInput() throws SQLException {
        userWishListSqliteDao.removeBookFromWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        verify(mockUserWishListJdbcHelper, times(1))
                .removeBookFromWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        verifyNoMoreInteractions(mockUserWishListJdbcHelper);
    }

    @Test
    public void getBookToWishList_Success_ForValidInput() throws SQLException {
        userWishListSqliteDao.getUserWishList(1);
        verify(mockUserWishListJdbcHelper, times(1))
                .getUserWishList(1);
        verifyNoMoreInteractions(mockUserWishListJdbcHelper);
    }

    @Test
    public void getBookToWishListByBook_Success_ForValidInput() throws SQLException {
        userWishListSqliteDao.getUserWishListByBook(1);
        verify(mockUserWishListJdbcHelper, times(1))
                .getUserWishListByBook(1);
        verifyNoMoreInteractions(mockUserWishListJdbcHelper);
    }

    @Test
    public void addBookToWishList_Failure_ForInValidInput() throws SQLException {
        doThrow(SQLException.class).when(mockUserWishListJdbcHelper).addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        try {
            userWishListSqliteDao.addBookToWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        } catch(InternalServiceError ex) {
            verify(mockUserWishListJdbcHelper, times(1))
                    .addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
            verifyNoMoreInteractions(mockUserWishListJdbcHelper);
            assertEquals("There are some internal errors adding your book to wishlist. Please try again.", ex.getMessage());
        }
    }

    @Test
    public void removeBookToWishList_Failure_ForInValidInput() throws SQLException {
        doThrow(SQLException.class).when(mockUserWishListJdbcHelper).removeBookFromWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        try {
            userWishListSqliteDao.removeBookFromWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        } catch(InternalServiceError ex) {
            verify(mockUserWishListJdbcHelper, times(1))
                    .removeBookFromWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
            verifyNoMoreInteractions(mockUserWishListJdbcHelper);
            assertEquals("Could not remove user wishlist. Please try again after sometime", ex.getMessage());
        }
    }

    @Test
    public void getBookToWishList_Failure_ForInValidInput() throws SQLException {
        doThrow(SQLException.class).when(mockUserWishListJdbcHelper).getUserWishList(1);
        try {
            userWishListSqliteDao.getUserWishList(1);
        } catch(InternalServiceError ex) {
            verify(mockUserWishListJdbcHelper, times(1))
                    .getUserWishList(1);
            verifyNoMoreInteractions(mockUserWishListJdbcHelper);
            assertEquals("Could not get UserWishList at this moment.", ex.getMessage());
        }
    }

    @Test
    public void getBookToWishListByBook_Failure_ForInValidInput() throws SQLException {
        doThrow(SQLException.class).when(mockUserWishListJdbcHelper).getUserWishListByBook(1);
        try {
            userWishListSqliteDao.getUserWishListByBook(1);
        } catch(InternalServiceError ex) {
            verify(mockUserWishListJdbcHelper, times(1))
                    .getUserWishListByBook(1);
            verifyNoMoreInteractions(mockUserWishListJdbcHelper);
            assertEquals("Could not get UserWishList at this moment.", ex.getMessage());
        }
    }
}
