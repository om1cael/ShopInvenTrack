package com.om1cael.controller;

import com.om1cael.dao.ProductDAO;
import com.om1cael.model.Product;

public class ProductController {
    ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
