package com.home.userbookwishlist.activity;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;
import com.home.userbookwishlist.model.Book;

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

    public Book retrieve(Integer isbn , String user_email ) {
        String sql = "SELECT * "
                + "FROM Book WHERE isbn == ? AND user_email = ?";

        Book book = new Book();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       // String result = "";
        try {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);

            pstmt.setInt(1,isbn);
            rs  = pstmt.executeQuery();


            // loop through the result set
            while (rs.next()) {
//                result = result + '\n' + rs.getInt("isbn") +  "\t" +
//                        rs.getString("author") + "\t" +
//                        rs.getString("title")
//                        + "\t" +
//                        rs.getDate("dateOfPublication");

                book.setIsbn(isbn);
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setDateOfPublication(rs.getString("dateOfPublication"));
                book.setUser_email(rs.getString("user_email"));
//
            }

            // set the corresponding param
//            pstmt.execute(sql);
//            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
