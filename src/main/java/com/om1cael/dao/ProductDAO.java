package com.om1cael.dao;

import com.om1cael.model.Product;
import com.om1cael.utils.DBConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAO {
    private final Connection connection;

    public ProductDAO(DBConnectionProvider dbConnectionProvider) {
        try {
            this.connection = dbConnectionProvider.getDBConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getSQLState());
        }
    }
}
