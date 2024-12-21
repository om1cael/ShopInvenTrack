package com.om1cael.controller;

import java.util.Scanner;

public class InputController {
    Scanner scanner;

    public InputController(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getNumberInput(int min, int max) {
        int input = scanner.nextInt();

        if(input >= min && input <= max) return input;
        return input < min ? min : max;
    }
}
