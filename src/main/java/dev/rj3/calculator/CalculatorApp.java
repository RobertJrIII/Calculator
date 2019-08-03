package dev.rj3.calculator;

import dev.rj3.calculator.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private FXMLLoader loader;
    private AnchorPane root;
    private Scene scene;
    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");
        controller.setStageAndScene(stage, scene);
        controller.setUpKeyPressed();
        setUpStage(stage);

        stage.show();
    }

    private void setUpStage(Stage stage) {
        stage.setMinHeight(360);
        stage.setMinWidth(280);
        stage.setResizable(false);

        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                stage.setMaximized(false);
        });
        stage.setScene(scene);
    }

    @Override
    public void init() throws Exception {
        loader = new FXMLLoader(getClass().getResource("fxml/calcUI.fxml"));
        root = loader.load();
        controller = loader.getController();
        scene = new Scene(root);
    }
}
