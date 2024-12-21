package com.om1cael;

import com.om1cael.controller.InputController;
import com.om1cael.dao.ProductDAO;
import com.om1cael.utils.DBConnectionProvider;
import com.om1cael.view.MenuView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputController inputController = new InputController(new Scanner(System.in));
        ProductDAO productDAO = new ProductDAO(new DBConnectionProvider());

        new MenuView(productDAO, inputController).showMenu();
    }
}