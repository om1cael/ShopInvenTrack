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
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
    }

    public boolean addProduct(Product product) {
        final String query = """
                             INSERT INTO stock (name, description, price, stock)
                             VALUES (?, ?, ?, ?)
                             """;

        try(PreparedStatement addProductStatement = this.connection.prepareStatement(query)) {
            addProductStatement.setString(1, product.name());
            addProductStatement.setString(2, product.description());
            addProductStatement.setDouble(3, product.price());
            addProductStatement.setInt(4, product.stock());

            addProductStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("It was not possible to add the product: " + e.getMessage());
        }
    }

    public boolean updateProduct(Product product, int id) {
        final String query = """
                             UPDATE stock
                             SET name=?, description=?, price=?, stock=?
                             WHERE id=?
                             """;

        try(PreparedStatement updateStatement = this.connection.prepareStatement(query)) {
            updateStatement.setString(1, product.name());
            updateStatement.setString(2, product.description());
            updateStatement.setDouble(3, product.price());
            updateStatement.setInt(4, product.stock());
            updateStatement.setInt(5, id);

            updateStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("It was not possible to update the product: " + e.getMessage());
        }
    }

    public boolean removeProduct(int id) {
        final String query = "DELETE FROM stock WHERE id=?";

        try(PreparedStatement deleteProductStatement = this.connection.prepareStatement(query)) {
            this.connection.setAutoCommit(false);
            deleteProductStatement.setInt(1, id);

            deleteProductStatement.execute();
            this.resetIDs();

            this.connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("It was not possible to remove the product: " + e.getMessage());
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Product getProduct(int id) {
        final String query = "SELECT * FROM stock WHERE id=?";

        try(PreparedStatement queryProductStatement = this.connection.prepareStatement(query)) {
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
        try(Statement queryAllStatement = this.connection.createStatement()) {
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

    private void resetIDs() {
        try(Statement statement = this.connection.createStatement()) {
            statement.execute("ALTER SEQUENCE stock_id_seq RESTART");
            statement.execute("UPDATE stock SET id = DEFAULT");
        } catch (SQLException e) {
            throw new RuntimeException("It was not possible to reset IDs: " + e.getMessage());
        }
    }
}
