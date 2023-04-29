package com.example.javaprojet1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class AddInterface extends Stage {

    public AddInterface() {


        // Create labels and textfields
        Label label1 = new Label("add the word in franche:");
        Label label2 = new Label("add the word in english :");
        Label label3 = new Label("add the word type :");
        Label label4 = new Label("add the example in french ");
        Label label5 = new Label("add the example in english");
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();

        Button b1=new Button("Add the Trad");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    String sql = "INSERT INTO dictionnaire (word_fr ,word_eng,type,exmple_fr,exmple_eng) VALUES (?, ?,?, ?,?)";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, textField1.getText());
                    stmt.setString(2,textField2.getText() );
                    stmt.setString(3, textField3.getText());
                    stmt.setString(4, textField4.getText());
                    stmt.setString(5, textField5.getText());
                    int rows = stmt.executeUpdate();
                    if (rows==1){
                        System.out.println("succes");
                        AdminInterface ad =new AdminInterface();
                        ad.show();
                        close();
                    }

                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });

        // Create grid pane and add labels and textfields to it
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);
        gridPane.add(label4, 0, 3);
        gridPane.add(textField4, 1, 3);
        gridPane.add(label5, 0, 4);
        gridPane.add(textField5, 1, 4);
        gridPane.add(b1, 1, 5);

        // Create scene and set it on the stage
        Scene scene = new Scene(gridPane, 400, 300);
        setScene(scene);
        setTitle("Add Form");
        show();
    }
        public static void main(String[] args) {

        }
    }

