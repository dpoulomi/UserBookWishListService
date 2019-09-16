package com.home.userbookwishlist.service;

import com.google.inject.Inject;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalUser;
import com.home.userbookwishlist.model.User;
import com.home.userbookwishlist.model.UserWishList;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class UserService {

    private final UserWishListDao userWishListDao;
    private final UserDao userDao;

    @Inject
    public UserService(final UserWishListDao userWishListDao, final UserDao userDao) {
        this.userWishListDao = userWishListDao;
        this.userDao = userDao;
    }

    public void addUser(final ExternalUser externalUser) {
        final User user = convertToUser(externalUser);
        userDao.addUser(user);
    }

    public String removeUser(final int userId) {
        if(isUserNotExist(userId)) {
            return String.format("user_id %d doesn't exist in our system.", userId);
        }

        List<UserWishList> userWishListResult = userWishListDao.getUserWishList(userId);
        if(!CollectionUtils.isEmpty(userWishListResult)) {
            userWishListResult.stream()
                    .forEach(userWishList -> userWishListDao.removeBookFromWishList(userWishList));
        }
        userDao.removeUser(userId);
        return String.format("user_id %d has been removed.", userId);
    }

    private User convertToUser(final ExternalUser externalUser) {
        return User.builder()
                .email(externalUser.getEmail())
                .firstName(externalUser.getFirstName())
                .lastName(externalUser.getLastName())
                .userId(externalUser.getUserId())
                .build();
    }

    private boolean isUserNotExist(final int userId) {
        return userDao.getUser(userId).size() == 0;
    }
}
