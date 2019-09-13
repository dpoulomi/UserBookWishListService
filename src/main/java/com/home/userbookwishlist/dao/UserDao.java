package com.home.userbookwishlist.dao;

import com.home.userbookwishlist.model.User;

public interface UserDao {

    void addUser(final User user);

    void removeUser(final int userId);

}
