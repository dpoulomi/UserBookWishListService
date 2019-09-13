package com.home.userbookwishlist.activity;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

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
    public void update(int isbn , String title , String author , String  dateOfPublication , String user_email) {
        String sql = "UPDATE book SET title = ? , "
                + "author = ? ,"
                + "dateOfPublication = ? ,"
                + "user_email = ?"
                + "WHERE isbn = ?";

        PreparedStatement pstmt = null;
        try {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);


        // set the corresponding param
            pstmt.setInt(1, isbn);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, dateOfPublication);
            pstmt.setString(5, user_email);
            // update
            int i =pstmt.executeUpdate();
            if (i > 0) {
                System.out.println("success");
            } else {
                System.out.println("stuck somewhere");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
   // }
}
