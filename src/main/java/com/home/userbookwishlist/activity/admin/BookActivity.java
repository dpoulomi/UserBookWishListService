package com.home.userbookwishlist.activity.admin;

import com.google.inject.Inject;
import com.home.userbookwishlist.exception.BadRequestException;
import com.home.userbookwishlist.exception.InternalServiceError;
import com.home.userbookwishlist.externalobject.ExternalBook;
import com.home.userbookwishlist.service.BookService;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
    public String addBook(@ModelAttribute final ExternalBook externalBook)
            throws InternalServiceError, BadRequestException  {
        try {
            return bookService.addBook(externalBook);
        } catch(final InternalServiceError ex){
            throw new BadRequestException("Please enter a different BookId"); // same bookId
        } catch (final Exception ex) {
            throw new InternalServiceError("addBook is down for sometime. Please try again later!");
        }
    }

    @GET
    @Path("/removeBook")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeBook(@QueryParam("bookId") final int bookId)
            throws InternalServiceError, BadRequestException {
        try {
            return bookService.removeBook(bookId);
        } catch(final InternalServiceError ex){
            throw ex;
        } catch (final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GET
    @Path("/getBook")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String getBook(@QueryParam("bookId") final int bookId)
            throws InternalServiceError, BadRequestException {
        try {
            return bookService.getBook(bookId);
        } catch(final InternalServiceError ex){
            throw ex;
        } catch (final Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
