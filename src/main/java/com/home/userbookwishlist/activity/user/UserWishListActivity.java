package com.home.userbookwishlist.activity.user;

import com.google.inject.Inject;
import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalUserWishList;
import com.home.userbookwishlist.service.UserWishListService;
import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @Inject
    public UserWishListActivity(final UserWishListService userWishListService) {
        this.userWishListService = userWishListService;
    }

    @POST
    @Path("/addToUserWishList")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addToWishList(@ModelAttribute final ExternalUserWishList externalUserWishList)
            throws BadRequestException, InternalServiceError {
        try {
            return userWishListService.addBookToUserWishList(externalUserWishList);
        } catch (final InternalServiceError ex) {
            throw ex;
        } catch(final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @POST
    @Path("/removeBookFromUserWishList")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeBookFromUserWishList(@ModelAttribute final ExternalUserWishList externalUserWishList)
            throws BadRequestException, InternalServiceError {
        try {
            return userWishListService.removeBookFromUserWishList(externalUserWishList);
        } catch (final InternalServiceError ex) {
            throw ex;
        } catch(final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
