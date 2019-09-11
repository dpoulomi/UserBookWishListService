package com.userbookwishlistmanagement.apifunctions;

import com.userbookwishlistmanagement.db.SQLConnectionObject;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateBookInWishList {



    /**
     * Update data of a warehouse specified by the id
     *
     * @param isbn unique identifier of the book
     * @param title name of the book
     * @param author of the book
     * @param dateOfPublication dateOfPublication of the book
     */
    public void update(Integer isbn , String title , String author , Date dateOfPublication) {
        String sql = "UPDATE book SET title = ? , "
                + "author = ? "
                + "dateOfPublication = ? "
                + "WHERE isbn = ?";


        PreparedStatement pstmt = null;
        try {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);


        // set the corresponding param
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDate(3, dateOfPublication);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
   // }
}
