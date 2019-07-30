package dev.rj3.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("fxml/calcUI.fxml"));
        Scene s = new Scene(anchorPane);
        primaryStage.setMinHeight(360);
        primaryStage.setMinWidth(280);
        primaryStage.setResizable(false);

        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });

        primaryStage.setScene(s);
        primaryStage.show();
    }
}
