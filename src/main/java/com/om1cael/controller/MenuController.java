package com.om1cael.controller;

import com.om1cael.dao.ProductDAO;
import com.om1cael.model.Product;

import java.util.List;

public class MenuController {
    ProductDAO productDAO;

    public MenuController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    public boolean updateProduct(Product product, int id) {
        if(productDAO.getProduct(id) == null) return false;
        return productDAO.updateProduct(product, id);
    }

    public boolean removeProduct(int id) {
        if(productDAO.getProduct(id) == null) return false;
        return productDAO.removeProduct(id);
    }

    public List<Product> listAllProducts() {
        return productDAO.getAllProducts();
    }
}
