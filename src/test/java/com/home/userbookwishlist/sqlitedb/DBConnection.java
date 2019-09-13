package com.home.userbookwishlist.sqlitedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public static void connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/neo/sqllite_db/sqllite_db.db";

            //"jdbc:sqlite:C:/sqlite/db/chinook.db";
            // create a connection to the database

            try {
                Class.forName("org.sqlite.JDBC").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection conn = DriverManager.getConnection(url);
            SQLConnectionObject.conn = conn;

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

        }
    }


}
