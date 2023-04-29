package com.example.javaprojet1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NewStage extends Stage {

    public NewStage() {
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> close());

        StackPane layout = new StackPane(closeButton);
        Scene scene = new Scene(layout, 200, 150);

        setTitle("New Stage");
        setScene(scene);
    }
}