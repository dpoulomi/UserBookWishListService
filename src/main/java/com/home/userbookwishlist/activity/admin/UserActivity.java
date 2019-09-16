package com.home.userbookwishlist.activity.admin;

import com.google.inject.Inject;
import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalUser;
import com.home.userbookwishlist.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/userService")
public class UserActivity {

    private final UserService userService;

    @Inject
    public UserActivity(final UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(@ModelAttribute final ExternalUser externalUser)
            throws InternalServiceError, BadRequestException  {
        try{
            userService.addUser(externalUser);
            return "User has been successfully added";
        } catch(final InternalServiceError ex){
            throw ex;
        } catch(final Exception ex){
            throw new InternalServiceError("addUser is down for sometime. Please try again later");
        }
    }

    @GET
    @Path("/removeUser")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeUser(@QueryParam("userId") final int userId)
                    throws InternalServiceError, BadRequestException {
        try{
            return userService.removeUser(userId);
        } catch(final InternalServiceError ex){
            throw ex;
        } catch(final Exception ex){
            throw new BadRequestException("addUser is down for sometime. Please try again later");
        }
    }
}
