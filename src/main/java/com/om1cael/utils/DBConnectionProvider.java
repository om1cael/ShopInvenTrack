package com.om1cael.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    final String url = "jdbc:postgresql://localhost:5432/shop";
    final String username = System.getenv("DB_USER");
    final String password = System.getenv("DB_PASSWORD");

    public Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
