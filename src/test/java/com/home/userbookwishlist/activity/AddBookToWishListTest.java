package com.home.userbookwishlist.activity;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookToWishListTest {
    public void addToWishList(int isbn , String title , String author , String dateOfPublication , int bookId) {


        String sql = "INSERT INTO Book (isbn,title,author, dateOfPublication, user_email) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try  {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);
            pstmt.setInt(1 , isbn);

            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, dateOfPublication);
            pstmt.setInt(5, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
