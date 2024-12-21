package com.om1cael.view;

import com.om1cael.controller.InputController;

import java.util.Scanner;

public class MenuView {
    InputController inputController;

    public MenuView(InputController inputController) {
        this.inputController = inputController;
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

    public void addProductUI() {}
    public void updateProductUI() {}
    public void removeProductUI() {}
    public void listProductUI() {}
    public void stockLogUI() {}

    private void handleInput() {
        System.out.print("Your choice: ");
        int input = inputController.getNumberInput(1, 6);

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
