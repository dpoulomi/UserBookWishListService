package com.userbookwishlistmanagement.userActivity;

import com.userbookwishlistmanagement.apifunctions.UpdateBookInWishList;
import com.userbookwishlistmanagement.apifunctions.ViewBookInWishList;
import com.userbookwishlistmanagement.db.DBConnection;
import com.userbookwishlistmanagement.entity.Book;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import javax.ejb.*;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//@Stateless
//@LocalBean
@Path("/userActivity")
public class UserActivity {

//    @PersistenceContext(unitName="UserService",
//            type=PersistenceContextType.TRANSACTION)
//    EntityManager entityManager;

//    @POST
//    @Consumes(MediaType.APPLICATION_XML)
//    public void create(Book book) {
//        entityManager.persist(book);
//    }




    @GET
    @Produces(MediaType.APPLICATION_XML)
   // @Path("{isbn}")
    public void read(@QueryParam("isbn") Integer isbn) {
        ViewBookInWishList viewBookInWishList = new ViewBookInWishList();
        DBConnection.connect();

        viewBookInWishList.retrieve(isbn);

    }

//    @POST
//    @Consumes(MediaType.APPLICATION_XML)
//    @Path("/book/{isbn}")
//    @Produces(MediaType.APPLICATION_XML)
//    public void update(@PathVariable Integer isbn , String title , String author , java.sql.Date dateOfPublication) {
//        UpdateBookInWishList updateBookInWishList = new UpdateBookInWishList();
//        updateBookInWishList.update(isbn, title, author, dateOfPublication);
//
//    }

//    @DELETE
//    @Path("{isbn}")
//    public void delete(@PathParam("isbn") long isbn) {
//        Book customer = read(isbn);
//        if(null != customer) {
//            entityManager.remove(customer);
//        }
//    }

//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    @Path("findCustomersByCity/{city}")
//    public List<Book> findCustomersByCity(@PathParam("city") String city) {
//        Query query = entityManager.createNamedQuery("findCustomersByCity");
//        query.setParameter("city", city);
//        return query.getResultList();
//    }

}