package com.example.javaprojet1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Button openNewStageButton = new Button("Open New Stage");
        openNewStageButton.setOnAction(e -> openNewStage());

        StackPane layout = new StackPane(openNewStageButton);
        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setTitle("Main Stage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openNewStage() {
        NewStage newStage = new NewStage();
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

