package dev.rj3.calculator.controller;

import dev.rj3.calculator.model.Calculate;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class Controller {


    @FXML
    private Label output;
    @FXML
    private GridPane pad;

    private String operator = "";
    private double num1 = 0;

    private Scene scene;
    @FXML
    private AnchorPane root;

    public void initialize() {

    }

    @FXML
    private void operatorClicked(MouseEvent e) {

        processOperator(((Button) e.getSource()).getText());
    }

    @FXML
    private void valueClicked(MouseEvent e) {
        String value = (((Button) e.getSource()).getText());
        processValue(value);

    }

    private void processOperator(String value) {

        if (!"=".equals(value)) {
            if (!operator.isEmpty()) return;

            operator = value;
            num1 = Double.parseDouble(output.getText());
            output.setText("");
        } else {

            if (operator.isEmpty()) return;

            double answer = round(Calculate.calculate(num1, Double.parseDouble(output.getText()), operator));

            String answerStr;

            if ((answer == Math.floor(answer)) && !Double.isInfinite(answer)) {
                answerStr = String.valueOf((int) answer);
            } else {
                answerStr = String.valueOf(answer);
            }


            output.setText(answerStr);
            operator = "";
        }
    }

    private double round(double answer) {

        return Math.round(100000000.0 * answer) / 100000000.0;
    }

    private void processValue(String source) {


        if (source.equals("+/‒")) {
            source = "-";
        }
        if (source.equals("-") && output.getText().contains("-") || source.equals(".") && output.getText().contains(".") || source.equals("-") && output.getText().length() > 1) {
            return;
        }

        if (output.getText().equals("") || (output.getText().equals("0") && output.getText().length() == 1)) {

            output.setText(source);

        } else {
            output.setText(output.getText() + source);
        }
    }


    @FXML
    private void clearLabel() {
        output.setText("0");
        num1 = 0;
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


                if (key.equals("%") || key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/") || key.equals("=") || keyCode.equals(KeyCode.ENTER)) {
                    if (keyCode.equals(KeyCode.ENTER)) {
                        key = "=";
                    }
                    processOperator(key);
                } else if (keyCode.equals(KeyCode.DELETE) || key.equals("C") || key.equals("c")) {
                    clearLabel();
                } else if (keyCode.isDigitKey() || key.equals(".")) {
                    processValue(key);
                } else if (keyCode.equals(KeyCode.BACK_SPACE)) {
                    String val = output.getText().substring(0, output.getText().length() - 1);
                    if (val.equals("")) {
                        val = String.valueOf(0);
                    }
                    output.setText(val);
                }

            });


        }

    }


}