package com.example.javaprojet1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;


public class exportone extends Stage {
    public exportone(){
                // Creating Label
                Label label = new Label("entre the word in english");

                // Creating Textfield
                TextField textField = new TextField();

                // Creating Button
                Button button = new Button("export this word ");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/test", "root", ""
                            );
                            Statement statement = connection.createStatement();
                            String req = "select * from dictionnaire where word_eng=?";
                            PreparedStatement stmt = connection.prepareStatement(req);
                            stmt.setString(1,textField.getText());
                            ResultSet resultSet =stmt.executeQuery();
                            while (resultSet.next()) {
                                BufferedWriter writer = new BufferedWriter(new FileWriter("output_one.txt", true));
                                writer.write(resultSet.getInt(1) + "," + resultSet.getString(2) + ","
                                        + resultSet.getString(3)+","+resultSet.getString(4)+","
                                        +resultSet.getString(5)+","+resultSet.getString(6));
                                writer.newLine();
                                writer.close();
                            }
                            stmt.close();
                            connection.close();
                        }

                        catch (Exception e1){
                            System.err.println(e1);
                        }
                        System.out.println("succes");
                        close();
                    }
                });
                // Creating HBox layout
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.setPadding(new Insets(10));
                hbox.setAlignment(Pos.CENTER);

                // Adding elements to HBox
                hbox.getChildren().addAll(label, textField, button);

                // Creating Scene and adding HBox to it
                Scene scene = new Scene(hbox, 400, 100);

                // Setting title of stage and scene and showing the scene
                setTitle("export one word");
                setScene(scene);
                show();
            }


        }


