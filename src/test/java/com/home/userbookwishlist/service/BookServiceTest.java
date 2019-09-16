package com.home.userbookwishlist.service;

import com.google.common.collect.Lists;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.UserWishList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class BookServiceTest {

    private static final ExternalBook EXTERNAL_BOOK = ExternalBook.builder()
            .bookId(1)
            .title("t")
            .author("a")
            .dateOfPublication("last year")
            .isbn(312334)
            .build();

    private static final Book BOOK = Book.builder()
            .bookId(1)
            .title("t")
            .author("a")
            .dateOfPublication("last year")
            .isbn(312334)
            .build();

    private static final UserWishList USER_WISH_LIST = UserWishList.builder()
            .bookId(1)
            .userId(1)
            .build();

    private BookService bookService;

    @Mock
    private UserWishListDao mockUserWishListDao;
    @Mock
    private BookDao mockBookDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(mockUserWishListDao, mockBookDao);
    }

    @Test
    public void addBook_Success_ValidInput() {
        String addBook = bookService.addBook(EXTERNAL_BOOK);
        assertEquals(addBook, "book_id : 1 has been added successfully");
        verify(mockBookDao, times(1)).addBook(any(Book.class));
        verify(mockBookDao, times(1)).getBook(1);
    }

    @Test
    public void addBook_Success_ExistingInput() {
        doReturn(Lists.newArrayList(EXTERNAL_BOOK)).when(mockBookDao).getBook(1);
        String addBook = bookService.addBook(EXTERNAL_BOOK);
        assertEquals(addBook, "book_id : 1 already exists.");
        verify(mockBookDao, times(0)).addBook(any(Book.class));
        verify(mockBookDao, times(1)).getBook(1);
        verifyNoMoreInteractions(mockBookDao);
    }

    @Test
    public void getBook_Success_ExistingBook() {
        doReturn(Lists.newArrayList(BOOK)).when(mockBookDao).getBook(1);
        String bookTitle = bookService.getBook(1);
        assertEquals(bookTitle, String.format("<h2>%s</h2>", EXTERNAL_BOOK.getTitle()));
        verify(mockBookDao, times(1)).getBook(1);
        verifyNoMoreInteractions(mockBookDao);
    }

    @Test
    public void removeBook_Success_ExistingBook() {
        doReturn(Lists.newArrayList(BOOK)).when(mockBookDao).getBook(1);
        doReturn(Lists.newArrayList(USER_WISH_LIST)).when(mockUserWishListDao).getUserWishListByBook(1);
        final String removeBookResponse = bookService.removeBook(1);
        assertEquals(removeBookResponse, "book_id 1 has been removed successfully.");
        verify(mockBookDao, times(1)).getBook(1);
        verify(mockUserWishListDao, times(1)).getUserWishListByBook(1);
        verify(mockBookDao, times(1)).removeBook(1);
        verify(mockUserWishListDao, times(1)).removeBookFromWishList(USER_WISH_LIST);
        verifyNoMoreInteractions(mockBookDao, mockUserWishListDao);
    }

    @Test
    public void removeBook_Success_NotExistingBook() {
        doReturn(Lists.newArrayList()).when(mockBookDao).getBook(1);
        final String removeBookResponse = bookService.removeBook(1);
        assertEquals(removeBookResponse, "book_id 1 doesn't exist in our system.");
        verify(mockBookDao, times(1)).getBook(1);
        verifyNoMoreInteractions(mockBookDao, mockUserWishListDao);
    }
}