package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.constants.Constants;
import com.home.userbookwishlist.model.UserWishList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserWishListJdbcHelperTest {

    private static final int SAMPLE_USER_ID = 12345;
    private static final int SAMPLE_BOOK_ID = 234455;

    private UserWishListJdbcHelper userWishListJdbcHelper;

    @Mock
    private Connection mockSqliteConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        doReturn(mockResultSet).when(mockPreparedStatement).executeQuery();
        userWishListJdbcHelper = new UserWishListJdbcHelper(mockSqliteConnection);
    }

    @Test
    public void getBookToUserWishList_Success_ValidInput() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());
        doReturn(true, false).when(mockResultSet).next();
        doReturn(SAMPLE_USER_ID).when(mockResultSet).getInt(Constants.UserWishListTableAttributeName.USER_ID);
        doReturn(SAMPLE_BOOK_ID).when(mockResultSet).getInt(Constants.UserWishListTableAttributeName.BOOK_ID);

        List<UserWishList> userWishList = userWishListJdbcHelper.getUserWishList(SAMPLE_USER_ID);
        assertNotNull(userWishList);
        assertEquals(1, userWishList.size());
        assertEquals(userWishList.get(0).getUserId(), SAMPLE_USER_ID);
        assertEquals(userWishList.get(0).getBookId(), SAMPLE_BOOK_ID);
        verify(mockPreparedStatement, times(1)).close();
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).getInt(any());
    }

    @Test
    public void getBookToUserWishList_Failure_InvalidInput() {
        try {
            doThrow(SQLException.class).when(mockSqliteConnection).prepareStatement(any());
            userWishListJdbcHelper.getUserWishList(SAMPLE_USER_ID);
            fail("Execution should not come here.");
        } catch (Exception ex) {
            assertTrue(ex instanceof SQLException);
        }
    }

    @Test
    public void addBookToUserWishList_Success_ValidInput() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());

        UserWishList userWishList = UserWishList.builder()
                                        .userId(SAMPLE_USER_ID)
                                        .bookId(SAMPLE_BOOK_ID)
                                        .build();

        userWishListJdbcHelper.addBookToUserWishList(userWishList);
        verify(mockPreparedStatement, times(1)).close();
        verify(mockPreparedStatement, times(1)).setInt(1, SAMPLE_USER_ID);
        verify(mockPreparedStatement, times(1)).setInt(2, SAMPLE_BOOK_ID);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void getBookToUserWishListByBook_Success_ValidInput() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());
        doReturn(true, false).when(mockResultSet).next();
        doReturn(SAMPLE_USER_ID).when(mockResultSet).getInt(Constants.UserWishListTableAttributeName.USER_ID);
        doReturn(SAMPLE_BOOK_ID).when(mockResultSet).getInt(Constants.UserWishListTableAttributeName.BOOK_ID);

        List<UserWishList> userWishList = userWishListJdbcHelper.getUserWishListByBook(SAMPLE_BOOK_ID);
        assertNotNull(userWishList);
        assertEquals(1, userWishList.size());
        assertEquals(userWishList.get(0).getUserId(), SAMPLE_USER_ID);
        assertEquals(userWishList.get(0).getBookId(), SAMPLE_BOOK_ID);
        verify(mockPreparedStatement, times(1)).close();
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).getInt(any());
        verify(mockPreparedStatement, times(1)).setInt(1, SAMPLE_BOOK_ID);
    }

    @Test
    public void removeBookToUserWishList_Success_ValidInput() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());

        UserWishList userWishList = UserWishList.builder()
                .userId(SAMPLE_USER_ID)
                .bookId(SAMPLE_BOOK_ID)
                .build();

        userWishListJdbcHelper.removeBookFromWishList(userWishList);
        verify(mockPreparedStatement, times(1)).close();
        verify(mockPreparedStatement, times(1)).setInt(1, SAMPLE_USER_ID);
        verify(mockPreparedStatement, times(1)).setInt(2, SAMPLE_BOOK_ID);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}

