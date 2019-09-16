package com.home.userbookwishlist.service;

import com.google.inject.Inject;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.externalobject.ExternalUserWishList;
import com.home.userbookwishlist.model.UserWishList;

import java.util.List;

public class UserWishListService {

    private final UserWishListDao userWishListDao;
    private final UserDao userDao;
    private final BookDao bookDao;

    @Inject
    public UserWishListService(final UserWishListDao userWishListDao,
                               final UserDao userDao,
                               final BookDao bookDao) {
        this.userWishListDao = userWishListDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    public String addBookToUserWishList(final ExternalUserWishList externalUserWishList) {
        UserWishList userWishList = convertToUserWishList(externalUserWishList);

        if(isUserNotExist(userWishList.getUserId())) {
            return String.format("user_id %d doesn't exist in our system. Please register first.",
                    userWishList.getUserId());
        }

        if(isBookNotExist(userWishList.getBookId())) {
            return String.format("book_id %d doesn't exist in our system. Please register the book first.",
                    userWishList.getBookId());
        }
        final List<UserWishList> userWishListResult = userWishListDao.getUserWishList(userWishList.getUserId());

        if(!isBookAlreasyExistInWishList(userWishListResult, userWishList)) {
            userWishListDao.addBookToWishList(userWishList);
            return String.format("book_id %d is added to wishlist of user_id: %d",
                    userWishList.getBookId(),
                    userWishList.getUserId());
        } else {
            return String.format("The book: %d already exists in user: %d 's wishlist",
                    userWishList.getBookId(),
                    userWishList.getUserId());
        }
    }

    public String removeBookFromUserWishList(final ExternalUserWishList externalUserWishList) {
        UserWishList userWishList = convertToUserWishList(externalUserWishList);

        if(isUserNotExist(userWishList.getUserId())) {
            return String.format("user_id %d doesn't exist in our system. Please register first.",
                    userWishList.getUserId());
        }

        if(isBookNotExist(userWishList.getBookId())) {
            return String.format("book_id %d doesn't exist in our system. Please register the book first.",
                    userWishList.getBookId());
        }

        List<UserWishList> userWishListResult = userWishListDao.getUserWishList(userWishList.getUserId());

        if(isBookAlreasyExistInWishList(userWishListResult, userWishList)) {
            userWishListDao.removeBookFromWishList(userWishList);
            return String.format("book_id %d is removed from wishlist of user_id: %d",
                    userWishList.getBookId(),
                    userWishList.getUserId());
        } else {
            return String.format("The book: %d doesn't exists in user: %d 's wishlist",
                    userWishList.getBookId(),
                    userWishList.getUserId());
        }
    }

    private UserWishList convertToUserWishList(final ExternalUserWishList externalUserWishList) {
        return UserWishList.builder()
                .userId(externalUserWishList.getUserId())
                .bookId(externalUserWishList.getBookId())
                .build();

    }

    private boolean isBookAlreasyExistInWishList(List<UserWishList> userWishListResult, UserWishList userWishList) {
        return userWishListResult.stream()
                .filter(userWishListRecord -> userWishListRecord.getBookId() == userWishList.getBookId())
                .findFirst()
                .isPresent();
    }

    private boolean isUserNotExist(final int userId) {
        return userDao.getUser(userId).size() == 0;
    }

    private boolean isBookNotExist(final int bookId) {
        return bookDao.getBook(bookId).size() == 0;
    }
}
