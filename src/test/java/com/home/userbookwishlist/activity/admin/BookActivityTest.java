package com.home.userbookwishlist.activity.admin;

import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookActivityTest {


    private BookActivity bookActivity;


    private ExternalBook externalBook = ExternalBook.builder()
            .bookId(1)
            .author("A")
            .title("T")
            .isbn(1)
            .dateOfPublication("02/08/2018")
            .build();

    @Mock
    private BookService mockBookService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookActivity = new BookActivity(mockBookService);
    }

    @Test
    public void addBook_Success_ForValidInput() throws BadRequestException {
        doReturn("Test").when(mockBookService).addBook(externalBook);
        String response = bookActivity.addBook(externalBook);
        assertEquals(response, "Test");
        verify(mockBookService, times(1)).addBook(externalBook);
        verifyNoMoreInteractions(mockBookService);
    }

    @Test
    public void addBook_Failure_ForInvalidInput() throws BadRequestException {
        try {
            doThrow(InternalServiceError.class).when(mockBookService).addBook(externalBook);
            bookActivity.addBook(externalBook);
        } catch (Exception ex) {
            String response = ex.getMessage();
            assertEquals(response, "Please enter a different BookId");
        }
    }

    @Test
    public void addBook_Failure_ForNonExistentInput() throws BadRequestException {
        try {
            doThrow(Exception.class).when(mockBookService).addBook(externalBook);
            bookActivity.addBook(externalBook);
            fail("Execution should not reach here");
        } catch (Exception ex) {
            String response = ex.getMessage();
            assertEquals(response, "addBook is down for sometime. Please try again later!");
        }
    }

    @Test
    public void removeBook_Success_ForValidInput() throws BadRequestException{
        doReturn("Test").when(mockBookService).removeBook(1);
        String response = bookActivity.removeBook(1);
        assertEquals(response, "Test");
        verify(mockBookService, times(1)).removeBook(1);
        verifyNoMoreInteractions(mockBookService);
    }
}
