package com.home.userbookwishlist.sqlitedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private final String databaseUrl;

    public SqliteConnection(final String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new RuntimeException("Could not load driver", ex);
        }
        try {
            return DriverManager.getConnection(databaseUrl);
        } catch (final SQLException e) {
            throw new RuntimeException("could not load database!"); // TODO
        }
    }
}
