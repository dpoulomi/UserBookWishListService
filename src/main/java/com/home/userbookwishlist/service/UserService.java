package com.home.userbookwishlist.service;

import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.externalobject.ExternalUser;
import com.home.userbookwishlist.sqlitedb.SqliteConnection;
import com.home.userbookwishlist.model.User;
import com.home.userbookwishlist.userapi.RemoveUser;
import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/userService")
public class UserService {


    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(final ExternalUser externalUser) {
        final User user = convertToUser(externalUser);
        userDao.addUser(user);
    }

    public void removeUser(final int userId) {
        userDao.removeUser(userId);
    }

    private User convertToUser(final ExternalUser externalUser) {
        return User.builder()
                .email(externalUser.getEmail())
                .firstName(externalUser.getFirstName())
                .lastName(externalUser.getLastName())
                .password(externalUser.getPassword())
                .userId(externalUser.getUserId())
                .build();
    }


//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/getUser")
//    public Book read(@QueryParam("user_mail") String user_email) {
//        GetBookInWishListActivity viewBookInWishList = new GetBookInWishListActivity();
//        SqliteConnection.connect();
//        return  viewBookInWishList.retrieve(user_email);
////        String bookDetails= viewBookInWishList.retrieve(isbn);
////            return "Book details for the isbn number: " +
////                    bookDetails;
//
//    }

//    @PUT
//    @Path("/updateUser")
//    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.TEXT_PLAIN)
////fetch the user name from user activity
//    public String updateBook(@ModelAttribute Book book) {
//        UpdateBookInWishListActivity updateBookInWishList = new UpdateBookInWishListActivity();
//        if(SQLConnectionObject.conn == null){
//            SqliteConnection.connect();
//        }
//        updateBookInWishList.update(book.getIsbn(), book.getAuthor() ,
//                book.getTitle() , book.getDateOfPublication(), book.getUser_email());
//        return "User updated";
//    }

    @GET
    @Path("/deleteUser")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("userId") final String userId ) {

        /*final RemoveUser removeUser = new RemoveUser();
        if(SQLConnectionObject.conn == null){
            SqliteConnection.connect();
        }
        removeUser.remove(userId);*/
        return "User and corresponding book list has been deleted";
    }
}
