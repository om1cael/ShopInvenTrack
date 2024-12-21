package com.om1cael;

import com.om1cael.dao.ProductDAO;
import com.om1cael.utils.DBConnectionProvider;


public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO(new DBConnectionProvider());
        productDAO.removeProduct(1);
    }
}