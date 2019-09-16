package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserJdbcHelperTest {

    private static final User SAMPLE_USER = User.builder()
            .userId(1)
            .firstName("first")
            .lastName("last")
            .email("first.last@home.com")
            .build();

    private UserJdbcHelper userJdbcHelper;

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
        userJdbcHelper = new UserJdbcHelper(mockSqliteConnection);
    }

    @Test
    public void addUser_Success_ValidInput() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());

        userJdbcHelper.addUser(SAMPLE_USER);
        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).setString(2, "first");
        verify(mockPreparedStatement, times(1)).setString(3, "last");
        verify(mockPreparedStatement, times(1)).setString(4, "first.last@home.com");
        verify(mockPreparedStatement, times(1)).close();
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void getBook_Success_ValidUser() throws SQLException {
        doReturn(true, false).when(mockResultSet).next();
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());
        doReturn(mockResultSet).when(mockPreparedStatement).executeQuery();
        doReturn(1).when(mockResultSet).getInt("user_id");
        doReturn("first").when(mockResultSet).getString("first_name");
        doReturn("last").when(mockResultSet).getString("last_name");
        doReturn("first.last@home.com").when(mockResultSet).getString("email");

        List<User> userList = userJdbcHelper.getUser(1);

        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(1, userList.get(0).getUserId());
        assertEquals("first", userList.get(0).getFirstName());
        assertEquals("last", userList.get(0).getLastName());
        assertEquals("first.last@home.com", userList.get(0).getEmail());

        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockResultSet, times(1)).getInt("user_id");
        verify(mockResultSet, times(1)).getString("first_name");
        verify(mockResultSet, times(1)).getString("last_name");
        verify(mockResultSet, times(1)).getString("email");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockPreparedStatement, times(1)).close();
        verify(mockSqliteConnection, times(1)).prepareStatement(any());
    }
}
