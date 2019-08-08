package dev.rj3.calculator.controller;

import dev.rj3.calculator.model.Calculate;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class Controller {


    @FXML
    private Label output;
    @FXML
    private GridPane pad;
    private final String NAN = "Not A Number";
    private String operator = "";

    private final double DEFAULT_VAL = 0;
    private double num1 = DEFAULT_VAL;
    private Scene scene;


    @FXML
    private void operatorClicked(MouseEvent e) {

        processOperator(((Button) e.getSource()).getText());
    }

    @FXML
    private void valueClicked(MouseEvent e) {
        processValue(((Button) e.getSource()).getText());

    }

    private void processOperator(String value) {

        if (!"=".equals(value)) {
            if (!operator.isEmpty()) return;

            operator = value;

            try {
                num1 = Double.parseDouble(getLabelText());
            } catch (NumberFormatException e) {
                setOutputText(NAN);

                return;
            }

            output.setText("");
        } else {

            if (operator.isEmpty()) return;
            double num2;
            try {
                num2 = Double.parseDouble(getLabelText());
            } catch (NumberFormatException e) {
                setOutputText(NAN);
                return;
            }

            double answer = Calculate.calculate(num1, num2, operator);

            setOutputText(processAnswer(answer));
            operator = "";
        }
    }


    private String processAnswer(double answer) {
        answer = round(answer);
        if (isInteger(answer)) {
            return String.valueOf((int) answer);
        } else {
            return String.valueOf(answer);
        }
    }

    private boolean isInteger(double answer) {
        return (answer == Math.floor(answer)) && !Double.isInfinite(answer);
    }

    private double round(double answer) {

        return Math.round(100000000.0 * answer) / 100000000.0;
    }

    private void processValue(String source) {


        if (source.equals("+/‒")) {
            source = "-";
        }
        if (source.equals("-") && getLabelText().contains("-") || source.equals(".") && getLabelText().contains(".") || (source.equals("-") && getLabelText().length() >= 1 && !getLabelText().equals("0"))) {
            return;
        }


        if (getLabelText().equals("") || (getLabelText().equals("0") && getLabelText().length() == 1)) {
            setOutputText(source);
        } else if (getLabelText().equals("Not a number")) {
            clearLabel();
            setOutputText(source);
        } else {
            setOutputText(getLabelText() + source);
        }
    }


    @FXML
    private void clearLabel() {
        setOutputText(String.valueOf((int) DEFAULT_VAL));
        num1 = DEFAULT_VAL;
        operator = "";
    }


    public void setScene(Scene scene) {

        this.scene = scene;

    }


    public void setUpKeyPressed() {
        if (scene != null) {
            scene.setOnKeyPressed(e -> {

                KeyCode keyCode = e.getCode();
                String key = e.getText();
                if (keyCode.equals(KeyCode.ENTER)) {
                    key = "=";
                }
                if (isOperator(key)) {
                    processOperator(key);
                } else if (keyCode.equals(KeyCode.DELETE)) {
                    clearLabel();
                } else if (keyCode.isDigitKey() || key.equals(".")) {
                    processValue(key);
                } else if (keyCode.equals(KeyCode.BACK_SPACE)) {
                    delete();
                }

            });


        }

    }

    private void delete() {
        String val = getLabelText().substring(0, output.getText().length() - 1);
        if (val.equals("")) {
            val = String.valueOf(0);
        }
        setOutputText(val);
    }

    private boolean isOperator(String key) {
        return key.equals("%") || key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/") || key.equals("=");
    }

    private String getLabelText() {
        return output.getText();
    }

    private void setOutputText(String val) {
        output.setText(val);
    }

}