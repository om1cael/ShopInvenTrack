package com.om1cael.dao;

import com.om1cael.model.Product;
import com.om1cael.utils.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
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

    public Product getProduct(int id) {
        final String query = "SELECT * FROM stock WHERE id=?";

        try(PreparedStatement queryProductStatement = connection.prepareStatement(query)) {
            queryProductStatement.setInt(1, id);
            ResultSet resultSet = queryProductStatement.executeQuery();

            if(resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("It was not possible to query the product: " + e.getSQLState());
        }

        return null;
    }

    public List<Product> getAllProducts() {
        try(Statement queryAllStatement = connection.createStatement()) {
            ResultSet resultSet = queryAllStatement.executeQuery("SELECT * FROM stock");
            List<Product> productList = new ArrayList<>();

            while(resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );

                productList.add(product);
            }

            return productList;
        } catch (SQLException e) {
            throw new RuntimeException("It was not possible to query all products: " + e.getSQLState());
        }
    }
}
