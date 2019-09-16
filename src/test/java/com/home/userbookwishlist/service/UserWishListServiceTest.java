package com.home.userbookwishlist.service;

import com.google.common.collect.Lists;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalUserWishList;
import com.home.userbookwishlist.model.Book;
import com.home.userbookwishlist.model.User;
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

public class UserWishListServiceTest {

    private static final ExternalUserWishList SAMPLE_EXTERNAL_USER_WISHLIST = ExternalUserWishList.builder()
            .userId(1)
            .bookId(1)
            .build();

    private static final UserWishList SAMPLE_USER_WISHLIST = UserWishList.builder()
            .userId(1)
            .bookId(1)
            .build();

    private static final Book SAMPLE_BOOK = Book.builder()
            .bookId(1)
            .title("t")
            .author("a")
            .dateOfPublication("last year")
            .isbn(312334)
            .build();

    private static final User SAMPLE_USER = User.builder()
            .userId(1)
            .firstName("first")
            .lastName("last")
            .email("email")
            .build();

    private UserWishListService userWishListService;

    @Mock
    private UserWishListDao mockUserWishListDao;
    @Mock
    private UserDao mockUserDao;
    @Mock
    private BookDao mockBookDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userWishListService = new UserWishListService(mockUserWishListDao,
                mockUserDao,
                mockBookDao);
        doReturn(Lists.newArrayList(SAMPLE_USER)).when(mockUserDao).getUser(1);
        doReturn(Lists.newArrayList(SAMPLE_BOOK)).when(mockBookDao).getBook(1);
    }

    @Test
    public void addBookToUserWishList_Success_ValidInput() {
        doReturn(Lists.newArrayList()).when(mockUserWishListDao).getUserWishList(1);
        final String result = userWishListService.addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        assertEquals("book_id 1 is added to wishlist of user_id: 1", result);
        verify(mockBookDao, times(1)).getBook(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(1)).addBookToWishList(any());
        verify(mockUserWishListDao, times(1)).getUserWishList(1);
        verifyNoMoreInteractions(mockBookDao, mockUserDao, mockUserWishListDao);
    }

    @Test
    public void addBookToUserWishList_Success_NotExistingUser() {
        doReturn(Lists.newArrayList()).when(mockUserDao).getUser(1);
        final String result = userWishListService.addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        assertEquals("user_id 1 doesn't exist in our system. Please register first.", result);
        verify(mockBookDao, times(0)).getBook(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(0)).addBookToWishList(any());
        verify(mockUserWishListDao, times(0)).getUserWishList(1);
        verifyNoMoreInteractions(mockBookDao, mockUserDao, mockUserWishListDao);
    }

    @Test
    public void addBookToUserWishList_Success_NotExistingBook() {
        doReturn(Lists.newArrayList()).when(mockBookDao).getBook(1);
        final String result = userWishListService.addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        assertEquals("book_id 1 doesn't exist in our system. Please register the book first.", result);
        verify(mockBookDao, times(1)).getBook(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(0)).addBookToWishList(any());
        verify(mockUserWishListDao, times(0)).getUserWishList(1);
        verifyNoMoreInteractions(mockBookDao, mockUserDao, mockUserWishListDao);
    }

    @Test
    public void addBookToUserWishList_Success_ExistingWishList() {
        doReturn(Lists.newArrayList(SAMPLE_USER_WISHLIST)).when(mockUserWishListDao).getUserWishList(1);
        final String result = userWishListService.addBookToUserWishList(SAMPLE_EXTERNAL_USER_WISHLIST);
        assertEquals("The book: 1 already exists in user: 1 's wishlist", result);
        verify(mockBookDao, times(1)).getBook(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(0)).addBookToWishList(any());
        verify(mockUserWishListDao, times(1)).getUserWishList(1);
        verifyNoMoreInteractions(mockBookDao, mockUserDao, mockUserWishListDao);
    }
}
