package com.om1cael;

import com.om1cael.controller.InputController;
import com.om1cael.view.MenuView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        new MenuView(new InputController(scanner)).showMenu();

        scanner.close();
    }
}