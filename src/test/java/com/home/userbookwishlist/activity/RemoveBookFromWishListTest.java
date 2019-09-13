package com.home.userbookwishlist.activity;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBookFromWishListTest {

    /**
     * Update data of a warehouse specified by the id
     *
     * @param isbn unique identifier of the book
    //     * @param title name of the book
    //     * @param author of the book
    //     * @param dateOfPublication dateOfPublication of the book
     */

    public void remove(Integer isbn  , String user_email) {
        String sql = "DELETE FROM Book WHERE isbn = ? AND user_email = ?";
        PreparedStatement pstmt = null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
