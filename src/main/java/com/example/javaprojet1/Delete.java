package com.example.javaprojet1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete extends Stage {

    public Delete(){

        Label label = new Label("Enter the word in french:");
        TextField textField = new TextField();
        Label label1 = new Label("Enter the exmple in french:");
        TextField textField1 = new TextField();
        Button button = new Button("Delete");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    String sql = "DELETE FROM dictionnaire WHERE  word_fr=? and exmple_fr=?";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, textField.getText());
                    stmt.setString(2, textField1.getText());
                    int rows = stmt.executeUpdate();
                    if (rows==1){
                        AdminInterface ad=new AdminInterface();
                        close();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("word not found");
                        alert.showAndWait();
                        textField.setText("");
                        textField1.setText("");
                    }

                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        // Create vertical box and add label, textfield, and button to it
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(label, textField,label1,textField1, button);

        // Create scene and set it on the stage
        Scene scene = new Scene(vBox, 300, 250);
        setScene(scene);
        setTitle("Delete page");
        show();
    }


}


