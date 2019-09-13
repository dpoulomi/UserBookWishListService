package com.home.userbookwishlist.activity.user;

import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.service.UserWishListService;
import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("/userWishListService")
public class UserWishListActivity {

    private final UserWishListService userWishListService;

    public UserWishListActivity(final UserWishListService userWishListService) {
        this.userWishListService = userWishListService;
    }

    @POST
    @Path("/addToWishList")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addToWishList(final int userId, final int bookId) throws BadRequestException, InternalServiceError {
        try {
            userWishListService.addBookToUserWishList(userId, bookId);
            return String.format("book_id %d is added to wishlist of user_id: %d", bookId, userId);
        } catch (final InternalServiceError ex) {
            throw ex;
        } catch(final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
