package com.home.userbookwishlist.activity.admin;

import com.google.inject.Inject;
import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.service.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/bookService")
public class BookActivity {

    private final BookService bookService;

    @Inject
    public BookActivity(final BookService bookService) {
        this.bookService = checkNotNull(bookService);
    }

    @POST
    @Path("/addBook")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addBook(final ExternalBook externalBook) throws InternalServiceError, BadRequestException  {
        try {
            bookService.addBook(externalBook);
            return "Book has been added successfully";
        } catch(final InternalServiceError ex){
            throw ex;
        } catch (final Exception ex) {
            throw new InternalServiceError("addBook is down for sometime. Please try again later!");
        }
    }

    @POST
    @Path("/removeBook")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(final int bookId) throws InternalServiceError, BadRequestException {
        try {
            bookService.removeBook(bookId);
            return "Book has been removed successfully";
        } catch(final InternalServiceError ex){
            throw ex;
        } catch (final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
