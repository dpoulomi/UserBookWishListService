package com.home.userbookwishlist.sqlitedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private final String databaseUrl;
    private final String jdbcDriverName;

    public SqliteConnection(final String databaseUrl, final String jdbcDriverName) {
        this.databaseUrl = databaseUrl;
        this.jdbcDriverName = jdbcDriverName;
    }

    public Connection connect() {
        try {
            Class.forName(jdbcDriverName).newInstance();
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
