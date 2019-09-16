package com.home.userbookwishlist.jdbc;

import com.home.userbookwishlist.model.Book;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class BookJdbcHelperTest {

    private static final Book BOOK = Book.builder()
            .bookId(1)
            .title("t")
            .author("a")
            .dateOfPublication("last year")
            .isbn(312334)
            .build();

    private BookJdbcHelper bookJdbcHelper;

    @Mock
    private Connection mockSqliteConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookJdbcHelper = new BookJdbcHelper(mockSqliteConnection);
    }

    @Test
    public void getBook_Success_ValidUser() throws SQLException {
        doReturn(true, false).when(mockResultSet).next();
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());
        doReturn(mockResultSet).when(mockPreparedStatement).executeQuery();
        doReturn(1).when(mockResultSet).getInt("book_id");
        doReturn(312334).when(mockResultSet).getInt("isbn");
        doReturn("t").when(mockResultSet).getString("title");
        doReturn("a").when(mockResultSet).getString("author");
        doReturn("last year").when(mockResultSet).getString("date_of_publication");

        List<Book> bookList = bookJdbcHelper.getBook(1);

        assertNotNull(bookList);
        assertEquals(1, bookList.size());
        assertEquals(1, bookList.get(0).getBookId());
        assertEquals(312334, bookList.get(0).getIsbn());
        assertEquals("t", bookList.get(0).getTitle());
        assertEquals("a", bookList.get(0).getAuthor());
        assertEquals("last year", bookList.get(0).getDateOfPublication());

        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockResultSet, times(1)).getInt("book_id");
        verify(mockResultSet, times(1)).getInt("isbn");
        verify(mockResultSet, times(1)).getString("title");
        verify(mockResultSet, times(1)).getString("author");
        verify(mockResultSet, times(1)).getString("date_of_publication");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockPreparedStatement, times(1)).close();
        verify(mockSqliteConnection, times(1)).prepareStatement(any());
    }

    @Test
    public void addBook_Success_ValidUser() throws SQLException {
        doReturn(mockPreparedStatement).when(mockSqliteConnection).prepareStatement(any());
        bookJdbcHelper.addBook(BOOK);
        verify(mockPreparedStatement, times(1)).setInt(1, 312334);
        verify(mockPreparedStatement, times(1)).setInt(5, 1);
        verify(mockPreparedStatement, times(1)).setString(2, "t");
        verify(mockPreparedStatement, times(1)).setString(3, "a");
        verify(mockPreparedStatement, times(1)).setString(4, "last year");
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).close();
        verify(mockSqliteConnection, times(1)).prepareStatement(any());
        verifyNoMoreInteractions(mockPreparedStatement, mockSqliteConnection);
    }
}
