package com.example.javaprojet1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

public class userinter extends Stage {
    public userinter(){

        Text text1 = new Text("word in english");

        //creating label password
        Text text2 = new Text("word in french");

        Text labe1 = new Text("Word In French :");
        Text label2 = new Text("Word In English :");
        Text label3 = new Text(" Type :");
        Text label5 = new Text(" Example in French :");
        Text label6 = new Text(" Example in English :");
        Text label7 = new Text(" Traduit the word in english :");
        Text label8 = new Text(" Traduit the word in French :");

        Text value1 = new Text("");
        Text value2 = new Text("");
        Text value3 = new Text("");
        Text value4 = new Text("");
        Text value5 = new Text("");

        //Creating Text Filed for email
        TextField textField1 = new TextField();

        //Creating Text Filed for password
        TextField textField2 = new TextField();

        //Creating Buttons
        Button button1 = new Button("translet to english");
        Button button3 = new Button("EXPORT ALL THE DATA IN TXT FILE ");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    String req = "select * from dictionnaire ";
                    PreparedStatement stmt = connection.prepareStatement(req);
                    ResultSet resultSet =stmt.executeQuery();
                    while (resultSet.next()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
                        writer.write(resultSet.getInt(1) + "," + resultSet.getString(2) + ","
                                + resultSet.getString(3)+","+resultSet.getString(4)+","
                                +resultSet.getString(5)+","+resultSet.getString(6));
                        writer.newLine();
                        writer.close();
                    }

                    stmt.close();
                    connection.close();
                }

                catch (Exception e){
                    System.err.println(e);
                }
            }
        });
        Button button4 = new Button("EXPORT ONE TRADUCTION IN TXT FILE");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exportone on=new exportone();
                on.show();

            }
        });



        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word_fr=textField2.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    String req = "select * from dictionnaire where word_fr= ?";
                    PreparedStatement stmt = connection.prepareStatement(req);
                    stmt.setString(1,word_fr);

                    ResultSet resultSet =stmt.executeQuery();

                    while (resultSet.next()) {
                        value1.setText(resultSet.getString(2));
                        value2.setText(resultSet.getString(3));
                        value3.setText(resultSet.getString(4));
                        value4.setText(resultSet.getString(5));
                        value5.setText(resultSet.getString(6));
                    }

                }
                catch (Exception e1){
                    System.err.println(e1);
                }
            }
        });

        Button button2 = new Button("translet to french");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word_eng=textField1.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    String req = "select * from dictionnaire where word_eng= ?";
                    PreparedStatement stmt = connection.prepareStatement(req);
                    stmt.setString(1,word_eng);

                    ResultSet resultSet =stmt.executeQuery();

                    while (resultSet.next()) {
                        value1.setText(resultSet.getString(2));
                        value2.setText(resultSet.getString(3));
                        value3.setText(resultSet.getString(4));
                        value4.setText(resultSet.getString(5));
                        value5.setText(resultSet.getString(6));
                    }

                }
                catch (Exception e1){
                    System.err.println(e1);
                }
            }
        });



        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(900, 500);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);


        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(button2, 1, 2);
        gridPane.add(labe1, 0, 3);
        gridPane.add(label2, 0, 4);
        gridPane.add(label3, 0, 5);
        gridPane.add(label5, 0, 6);
        gridPane.add(label6, 0, 7);



        gridPane.add(value1, 1, 3);
        gridPane.add(value2, 1, 4);
        gridPane.add(value3, 1, 5);
        gridPane.add(value4, 1, 6);
        gridPane.add(value5, 1, 7);
        gridPane.add(button3, 0, 8);
        gridPane.add(button4, 1, 8);


                // Create scene
                Scene scene = new Scene(gridPane, 700, 500);

                // Set stage title and scene
                setTitle("My Interface");
                setScene(scene);
                // Show stage
                show();
            }


        }
