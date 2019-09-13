package com.home.userbookwishlist.activity.user;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBookFromWishListActivity {

    /**
     * Update data of a warehouse specified by the id
     *
     * @param isbn unique identifier of the book
    //     * @param title name of the book
    //     * @param author of the book
    //     * @param dateOfPublication dateOfPublication of the book
     */

    public String remove(Integer isbn  , String user_email) {
        String sql = "DELETE FROM Book WHERE isbn = ? AND user_email = ?";
        PreparedStatement pstmt = null;
        String result = "";
        try {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);
//            ResultSet rs  = pstmt.executeQuery();
//
//            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("isbn") +  "\t" +
//                        rs.getString("author") + "\t" +
//                        rs.getString("title")
//                        + "\t" +
//                        rs.getDate("dateOfPublication"));
//            }
//
//            // set the corresponding param
//            pstmt.execute(sql);
            pstmt.setInt(1, isbn);
            pstmt.setString(2, user_email);
            pstmt.executeUpdate();
            return "Book with isbn" + isbn + "and user_email" + user_email + "has been deleted";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Book could not be deleted due to an exception";
        }
    }
}
