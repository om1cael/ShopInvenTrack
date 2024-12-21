package com.om1cael.controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputController {
    Scanner scanner;

    public InputController(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getTextInput(String message) {
        String input = null;

        do {
            System.out.print(message);
            input = scanner.nextLine();
        } while (input.isEmpty());

        return input;
    }

    public double getNumberInput(String message, int min, int max) {
        try {
            System.out.print(message);
            String input = scanner.nextLine();
            double inputAsDouble = Double.parseDouble(input);

            if(inputAsDouble >= min && inputAsDouble <= max) return inputAsDouble;
            return inputAsDouble < min ? min : max;
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new RuntimeException("It was not possible to parse the numeric input: " + e.getMessage());
        }
    }
}
