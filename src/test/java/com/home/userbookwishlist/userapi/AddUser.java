package com.home.userbookwishlist.userapi;

import com.home.userbookwishlist.sqlitedb.SQLConnectionObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {
    public void addUser(String first_name , String last_name , String user_email , String password) {


        String sql = "INSERT INTO user (first_name ,last_name ,email , password) VALUES(?,?,?,?)";
        PreparedStatement pstmt = null;

        try  {
            pstmt = SQLConnectionObject.conn.prepareStatement(sql);
            pstmt.setString(1 , first_name);

            pstmt.setString(2, last_name);
            pstmt.setString(3, user_email);
            pstmt.setString(4, password);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
