package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.User;

import java.util.List;

public interface UserDao {

    void addUser(final User user);

    List<User> getUser(final int userId);

    void removeUser(final int userId);

}
