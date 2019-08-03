package dev.rj3.calculator.model;

public class Calculate {


    public static double calculate(double num1, double num2, String operator) {

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "%":
                return num1 % num2;
            case "/":
                if (num2 == 0) return Double.NaN;
                return num1 / num2;
        }
        return Double.NaN;
    }

}