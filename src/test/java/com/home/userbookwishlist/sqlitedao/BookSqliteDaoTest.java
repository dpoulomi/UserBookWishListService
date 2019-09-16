package com.home.userbookwishlist.sqlitedao;

import com.google.common.collect.Lists;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class BookSqliteDaoTest {

    private static final Book SAMPLE_BOOK = Book.builder()
            .bookId(1)
            .title("t")
            .author("a")
            .dateOfPublication("last year")
            .isbn(312334)
            .build();

    private BookDao bookDao;

    @Mock
    private BookJdbcHelper mockBookJdbcHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookDao = new BookSqliteDao(mockBookJdbcHelper);
    }

    @Test
    public void addBook_Success_ForValidBook() throws SQLException {
        bookDao.addBook(SAMPLE_BOOK);
        verify(mockBookJdbcHelper, times(1)).addBook(SAMPLE_BOOK);
        verifyNoMoreInteractions(mockBookJdbcHelper);
    }

    @Test
    public void addBook_Failure_ForAlreadyExistingBook() throws SQLException {
        try {
            doThrow(SQLException.class).when(mockBookJdbcHelper).addBook(SAMPLE_BOOK);
            bookDao.addBook(SAMPLE_BOOK);
        } catch(InternalServiceError ex) {
            verify(mockBookJdbcHelper, times(1)).addBook(SAMPLE_BOOK);
            verifyNoMoreInteractions(mockBookJdbcHelper);
            assertEquals("Add Book api is down for sometime. Please try again later!", ex.getMessage());
        }
    }

    @Test
    public void getBook_Failure_ForInvalidInput() throws SQLException {
        try {
            doThrow(SQLException.class).when(mockBookJdbcHelper).getBook(1);
            bookDao.getBook(1);
        } catch(InternalServiceError ex) {
            verify(mockBookJdbcHelper, times(1)).getBook(1);
            verifyNoMoreInteractions(mockBookJdbcHelper);
            assertEquals("Add Book api is down for sometime. Please try again later!", ex.getMessage());
        }
    }

    @Test
    public void removeBook_Success_ForValidBook() throws SQLException {
        bookDao.removeBook(1);
        verify(mockBookJdbcHelper, times(1)).removeBook(1);
        verifyNoMoreInteractions(mockBookJdbcHelper);
    }

    @Test
    public void removeBook_Failure_ForInvalidInput() throws SQLException {
        try {
            doThrow(SQLException.class).when(mockBookJdbcHelper).removeBook(1);
            bookDao.removeBook(1);
        } catch(InternalServiceError ex) {
            verify(mockBookJdbcHelper, times(1)).removeBook(1);
            verifyNoMoreInteractions(mockBookJdbcHelper);
            assertEquals("Add Book api is down for sometime. Please try again later!", ex.getMessage());
        }
    }

    @Test
    public void getBook_Success_ForValidBook() throws SQLException {
        doReturn(Lists.newArrayList(SAMPLE_BOOK)).when(mockBookJdbcHelper).getBook(1);
        List<Book> bookList = bookDao.getBook(1);
        assertNotNull(bookList);
        assertEquals(1, bookList.size());
        assertEquals(1, bookList.get(0).getBookId());
        assertEquals(312334, bookList.get(0).getIsbn());
        assertEquals("t", bookList.get(0).getTitle());
        assertEquals("a", bookList.get(0).getAuthor());
        assertEquals("last year", bookList.get(0).getDateOfPublication());

        verify(mockBookJdbcHelper, times(1)).getBook(1);
        verifyNoMoreInteractions(mockBookJdbcHelper);
    }
}
