package com.om1cael.view;

import com.om1cael.controller.InputController;
import com.om1cael.controller.MenuController;
import com.om1cael.model.Product;

import java.util.List;

public class MenuView {
    InputController inputController;
    MenuController menuController;

    public MenuView(InputController inputController, MenuController menuController) {
        this.inputController = inputController;
        this.menuController = menuController;
    }

    public void showMenu() {
        System.out.println("[1] Add Product");
        System.out.println("[2] Update Product");
        System.out.println("[3] Remove Product");
        System.out.println("[4] List Products");
        this.handleInitialInput();
    }

    public void addProductUI() {
        Product product = getProduct();

        if(menuController.addProduct(product)) {
            System.out.println("The product " + product.name() + " was added successfully!");
            return;
        }

        System.out.println("The product could not be added.");
    }

    public void updateProductUI() {
        int id = (int)inputController.getNumberInput("Product ID: ", 0, Integer.MAX_VALUE);
        Product product = getProduct();

        if(menuController.updateProduct(product, id)) {
            System.out.println("The product " + product.name() + " was edited successfully!");
            return;
        }

        System.out.println("It was not possible to edit the product.");
    }



    public void removeProductUI() {
        int id = (int)inputController.getNumberInput("Product ID: ", 0, Integer.MAX_VALUE);
        boolean productRemoved = menuController.removeProduct(id);

        if(productRemoved) {
            System.out.println("The product was removed successfully!");
            return;
        }

        System.out.println("It was not possible to remove the product, or it does not exists.");
    }

    public void listProductUI() {
        List<Product> productList = menuController.listAllProducts();
        if(productList == null || productList.isEmpty()) {
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

    private Product getProduct() {
        String name = inputController.getTextInput("Product name: ");
        String description = inputController.getTextInput("Product description: ");
        double price = inputController.getNumberInput("Product price: ", 0, Integer.MAX_VALUE);
        int stock = (int)inputController.getNumberInput("Product stock: ", 0, Integer.MAX_VALUE);
        return new Product(0, name, description, price, stock);
    }

    private void handleInitialInput() {
        int input = (int)inputController.getNumberInput("Your choice: ", 1, 4);

        switch(input) {
            case 1 -> addProductUI();
            case 2 -> updateProductUI();
            case 3 -> removeProductUI();
            case 4 -> listProductUI();
        }
    }
}
