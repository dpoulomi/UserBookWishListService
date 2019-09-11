package com.userbookwishlistmanagement.apifunctions;

import com.userbookwishlistmanagement.db.SQLConnectionObject;
import com.userbookwishlistmanagement.entity.Book;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBookInWishList {



    /**
     * Update data of a warehouse specified by the id
     *
     * @param isbn unique identifier of the book
//     * @param title name of the book
//     * @param author of the book
//     * @param dateOfPublication dateOfPublication of the book
     */

    public void retrieve(Integer isbn ) {
        String sql = "SELECT * "
                + "FROM Book WHERE isbn == isbn";


        PreparedStatement pstmt = null;
        try {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("isbn") +  "\t" +
                        rs.getString("author") + "\t" +
                        rs.getString("title")
                        + "\t" +
                        rs.getDate("dateOfPublication"));
            }

            // set the corresponding param
            pstmt.execute(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
