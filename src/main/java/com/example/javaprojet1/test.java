package com.example.javaprojet1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class test extends Application {
    @Override
    public void start(Stage s) throws Exception {
            // set title for the stage
            s.setTitle("creating buttons");

            // create a button
            Button b = new Button("button");

            // create a stack pane
            StackPane r = new StackPane();

            // add button
            r.getChildren().add(b);

            // create a scene
            Scene sc = new Scene(r, 200, 200);

            // set the scene
            s.setScene(sc);

            s.show();
        }
    public static void main(String args[]){
        launch(args);

    }

}
