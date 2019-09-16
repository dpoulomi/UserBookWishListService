package com.home.userbookwishlist.service;

import com.google.common.collect.Lists;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalUser;
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

public class UserServiceTest {

    private static final UserWishList SAMPLE_USER_WISHLIST = UserWishList.builder()
            .userId(1)
            .bookId(1)
            .build();

    private static final User SAMPLE_USER = User.builder()
            .userId(1)
            .firstName("first")
            .lastName("last")
            .email("email")
            .build();

    private static final ExternalUser SAMPLE_EXTERNAL_USER = ExternalUser.builder()
            .userId(1)
            .firstName("first")
            .lastName("last")
            .email("email")
            .build();

    private UserService userService;

    @Mock
    private UserWishListDao mockUserWishListDao;
    @Mock
    private UserDao mockUserDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(mockUserWishListDao, mockUserDao);
    }

    @Test
    public void addUser_Success_ValidUser() {
        userService.addUser(SAMPLE_EXTERNAL_USER);
        verify(mockUserDao, times(1)).addUser(any());
        verifyNoMoreInteractions(mockUserDao);
    }

    @Test
    public void removeUser_Success_ValidUser() {
        doReturn(Lists.newArrayList(SAMPLE_USER)).when(mockUserDao).getUser(1);
        doReturn(Lists.newArrayList(SAMPLE_USER_WISHLIST)).when(mockUserWishListDao).getUserWishList(1);
        String result = userService.removeUser(1);
        assertEquals("user_id 1 has been removed.", result);
        verify(mockUserDao, times(1)).removeUser(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(1)).getUserWishList(1);
        verify(mockUserWishListDao, times(1)).removeBookFromWishList(any());
        verifyNoMoreInteractions(mockUserDao, mockUserWishListDao);
    }

    @Test
    public void removeUser_Success_NonExistingUser() {
        doReturn(Lists.newArrayList()).when(mockUserDao).getUser(1);
        String result = userService.removeUser(1);
        assertEquals("user_id 1 doesn't exist in our system.", result);
        verify(mockUserDao, times(0)).removeUser(1);
        verify(mockUserDao, times(1)).getUser(1);
        verify(mockUserWishListDao, times(0)).getUserWishList(1);
        verify(mockUserWishListDao, times(0)).removeBookFromWishList(any());
        verifyNoMoreInteractions(mockUserDao, mockUserWishListDao);
    }
}
