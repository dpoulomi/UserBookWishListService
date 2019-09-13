package com.home.userbookwishlist.userapi;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveUser {

    /**
     * Update data of a warehouse specified by the id
     *
     * @param user_email unique identifier of the book
    //     * @param title name of the book
    //     * @param author of the book
    //     * @param dateOfPublication dateOfPublication of the book
     */

    public void remove(String user_email ) {
        String sql = "DELETE FROM User WHERE user_email = ?";


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
            pstmt.setString(1, user_email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
