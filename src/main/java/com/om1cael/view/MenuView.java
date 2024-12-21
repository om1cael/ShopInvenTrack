package com.om1cael.view;

import com.om1cael.controller.InputController;
import com.om1cael.dao.ProductDAO;
import com.om1cael.model.Product;

import java.util.List;
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
        Product product = getProduct();

        if(productDAO.addProduct(product)) {
            System.out.println("Product " + product.name() + " was added successfully!");
            return;
        }

        System.out.println("The product could not be added.");
    }

    public void updateProductUI() {
        int id = (int)inputController.getNumberInput("Product ID: ", 0, Integer.MAX_VALUE);
        if(productDAO.getProduct(id) == null) {
            System.out.println("The product does not exists.");
            return;
        }

        Product product = getProduct();

        if(productDAO.updateProduct(product, id)) {
            System.out.println("Product " + product.name() + " updated successfully!");
            return;
        }

        System.out.println("It was not possible to edit the product.");
    }



    public void removeProductUI() {
        int id = (int)inputController.getNumberInput("Product ID: ", 0, Integer.MAX_VALUE);
        if(productDAO.getProduct(id) == null) {
            System.out.println("The product does not exists.");
            return;
        }

        if(productDAO.removeProduct(id)) {
            System.out.println("Product removed successfully!");
            return;
        }

        System.out.println("It was not possible to remove the product.");
    }

    public void listProductUI() {
        List<Product> productList = productDAO.getAllProducts();
        if(productList == null) {
            System.out.println("No products to show.");
            return;
        }

        System.out.println("\nProduct list:");
        for (Product product : productList) {
            System.out.printf(" * %s (ID %d): %n", product.name(), product.id());
            System.out.println("  - " + product.description());
            System.out.println("  - $" + product.price());
            System.out.println("  - " + product.stock() + " units");
        }
    }

    public void stockLogUI() {}

    private Product getProduct() {
        String name = inputController.getTextInput("Product name: ");
        String description = inputController.getTextInput("Product description: ");
        double price = inputController.getNumberInput("Product price: ", 0, Integer.MAX_VALUE);
        int stock = (int)inputController.getNumberInput("Product stock: ", 0, Integer.MAX_VALUE);
        return new Product(0, name, description, price, stock);
    }

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
