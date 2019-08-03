package dev.rj3.calculator.controller;

import dev.rj3.calculator.model.Calculate;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Controller {


    @FXML
    private Label output;
    @FXML
    private GridPane pad;
    @FXML
    private AnchorPane root;

    private String operator = "";
    private double num1 = 0;
    private Calculate model;
    private Stage stage;
    private Scene scene;


    public void initialize() {
        model = new Calculate();
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

            double num2;

            try {
                num2 = Double.parseDouble(output.getText());
            } catch (NumberFormatException ne) {
                num2 = Double.NaN;
            }

            output.setText(String.valueOf(model.calculate(num1, num2, operator)));
            operator = "";
        }
    }

    public void processValue(String source) {

        String value = source;

        if (value.equals("+/‒")) {
            value = "-";
        }

        if (output.getText().equals("") || (output.getText().equals("0") && output.getText().length() == 1)) {

            output.setText(value);

        } else {

            output.setText(output.getText() + value);
        }
    }



    @FXML
    private void clearLabel() {
        output.setText("0");
        num1 = 0;
        operator = "";
    }

    public void setStage(Stage stage) {
        this.stage = stage;


    }

    public void setStageAndScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;

    }


    public void setUpKeyPressed() {
        if (scene != null) {
            scene.setOnKeyPressed(e -> {

                processValue(e.getCode().getName());

            });
        }

    }
}