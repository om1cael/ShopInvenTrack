package com.om1cael.view;

import com.om1cael.controller.InputController;
import com.om1cael.dao.ProductDAO;
import com.om1cael.model.Product;

import java.util.Scanner;

public class MenuView {
    ProductDAO productDAO;
    InputController inputController;

    public MenuView(ProductDAO productDAO, InputController inputController) {
        this.inputController = inputController;
        this.productDAO = productDAO;
    }

    public void showMenu() {
        System.out.println("[1] Add Product");
        System.out.println("[2] Update Product");
        System.out.println("[3] Remove Product");
        System.out.println("[4] List Products");
        System.out.println("[5] List Products With Filter");
        System.out.println("[6] Stock Log");
        this.handleInput();
    }

    public void addProductUI() {
        String name = inputController.getTextInput("Product name: ");
        String description = inputController.getTextInput("Product description: ");
        double price = inputController.getNumberInput("Product name: ", 0, Integer.MAX_VALUE);
        int stock = (int)inputController.getNumberInput("Product stock: ", 0, Integer.MAX_VALUE);
        Product product = new Product(0, name, description, price, stock);

        if(productDAO.addProduct(product)) {
            System.out.println("Product " + product.name() + " was added successfully!");
            return;
        }

        System.out.println("The product could not be added.");
    }

    public void updateProductUI() {}
    public void removeProductUI() {}
    public void listProductUI() {}
    public void stockLogUI() {}

    private void handleInput() {
        int input = (int)inputController.getNumberInput("Your choice: ", 1, 6);

        switch(input) {
            case 1 -> addProductUI();
            case 2 -> updateProductUI();
            case 3 -> removeProductUI();
            case 4 -> listProductUI();
            case 5 -> listProductUI();
            case 6 -> stockLogUI();
        }
    }
}
