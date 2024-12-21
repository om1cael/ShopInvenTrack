package com.om1cael.controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputController {
    Scanner scanner;

    public InputController(Scanner scanner) {
        this.scanner = scanner;
    }

    public double getNumberInput(int min, int max) {
        try {
            String input = scanner.nextLine();
            double inputAsDouble = Double.parseDouble(input);

            if(inputAsDouble >= min && inputAsDouble <= max) return inputAsDouble;
            return inputAsDouble < min ? min : max;
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new RuntimeException("It was not possible to parse the numeric input: " + e.getMessage());
        }
    }
}
