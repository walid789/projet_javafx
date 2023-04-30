package com.example.javaprojet1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class userinter extends Stage {
    int size=1;
    private String[] words_fr =new String[size];
    private String[] word_en = new String[size];
    public  ArrayList<String> List = new ArrayList<>();

    public userinter() {

        /*add the all the langage in table*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", ""
            );
            Statement statement = connection.createStatement();
            String req = "select word_fr,word_eng from dictionnaire ";
            PreparedStatement stmt = connection.prepareStatement(req);

            ResultSet resultSet = stmt.executeQuery();
            int i = 0;

            int v=1;
            while (resultSet.next()) {
                words_fr = Arrays.copyOf(words_fr, v);
                word_en = Arrays.copyOf(word_en, v);
                words_fr[i]=resultSet.getString(1);
                word_en[i]=resultSet.getString(2);
                i++;
                v++;
            }
            size=i;
        } catch (Exception e) {
            System.err.println(e);
        }

        Text text1 = new Text("Add the word ");

        //creating label password
        Text text2 = new Text("");
        CheckBox c = new CheckBox("traduit from english to french");
        CheckBox c1 = new CheckBox("traduit from french to english");



        //Creating Text Filed for email
        TextField textField1 = new TextField();

        textField1.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(c1.isSelected()) {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("User entered: " + textField1.getText());
            } else {
                String enteredText = textField1.getText();
                if (!enteredText.isEmpty()) {
                    String[] filteredWords = getFilteredWords(enteredText);
                    if (filteredWords.length > 0) {
                        textField1.setText(filteredWords[0]);
                        textField1.selectRange(enteredText.length(), filteredWords[0].length());
                    }
                }
            }
            }
            if(c.isSelected()){
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("User entered: " + textField1.getText());
                } else {
                    String enteredText = textField1.getText();
                    if (!enteredText.isEmpty()) {
                        String[] filteredWords = getFilteredWords_eng(enteredText);
                        if (filteredWords.length > 0) {
                            textField1.setText(filteredWords[0]);
                            textField1.selectRange(enteredText.length(), filteredWords[0].length());
                        }
                    }
                }
            }

        });

        TextField textField2 = new TextField();
        textField2.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("User entered: " + textField2.getText());
            } else {
                String enteredText = textField2.getText();
                if (!enteredText.isEmpty()) {
                    String[] filteredWords = getFilteredWords_eng(enteredText);
                    if (filteredWords.length > 0) {
                        textField2.setText(filteredWords[0]);
                        textField2.selectRange(enteredText.length(), filteredWords[0].length());
                    }
                }
            }
        });


        //Creating Text Filed for password


        //Creating Buttons
        Button button1 = new Button("translet");
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
                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
                        writer.write(resultSet.getInt(1) + "," + resultSet.getString(2) + ","
                                + resultSet.getString(3) + "," + resultSet.getString(4) + ","
                                + resultSet.getString(5) + "," + resultSet.getString(6));
                        writer.newLine();
                        writer.close();
                    }

                    stmt.close();
                    connection.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });

        Button button4 = new Button("EXPORT ONE TRADUCTION IN TXT FILE");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exportone on = new exportone();
                on.show();

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


        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                String word = textField1.getText();
                List.clear();
                if(word==""){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("plz entre un word ");
                    alert.showAndWait();
                } else if (!c1.isSelected() && !c.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("plz select one of the chekbox ");
                    alert.showAndWait();
                }
                else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    if(c1.isSelected()){
                        String req = "select * from dictionnaire where word_fr= ?";
                        PreparedStatement stmt = connection.prepareStatement(req);
                        stmt.setString(1, word);
                        ResultSet resultSet = stmt.executeQuery();


                        while (resultSet.next()) {
                            List.add(resultSet.getString(2)+" "+resultSet.getString(3)+" "
                                    +resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6));
                        }


                    }
                    if(c.isSelected()){
                        System.out.println("test");
                        String req = "select * from dictionnaire where word_eng= ?";
                        PreparedStatement stmt = connection.prepareStatement(req);
                        stmt.setString(1, word);
                        ResultSet resultSet = stmt.executeQuery();
                        while (resultSet.next()) {
                            List.add(resultSet.getString(2) + " " + resultSet.getString(3) + " "
                                    + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
                        }


                    }
                    if(c1.isSelected() ||c.isSelected()){
                        if(List.size()==0){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("this word is note found");
                            alert.showAndWait();
                            List.clear();
                        }
                        else {
                            ObservableList<String> items = FXCollections.observableArrayList(List);
                            System.out.println(items);
                            ListView<String> listView = new ListView<>(items);
                            gridPane.add(listView, 0, 3);
                        }
                    }
                } catch (Exception e1) {
                    System.err.println(e1);
                }
            }}
        });


        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(c, 0, 1);
        gridPane.add(c1, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(text2,1,2);
        gridPane.add(button3, 0, 4);
        gridPane.add(button4, 1, 4);
        c.selectedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue && c1.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous ne pouvez pas sélectionner les deux options en même temps");
                alert.showAndWait();
                c.setSelected(false);
            }
        });

        c1.selectedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue && c.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous ne pouvez pas sélectionner les deux options en même temps");
                alert.showAndWait();
                c1.setSelected(false);
            }
        });


        // Create scene
        Scene scene = new Scene(gridPane, 700, 500);

        // Set stage title and scene
        setTitle("My Interface");
        setScene(scene);
        // Show stage
        show();

    }

    private String[] getFilteredWords_eng(String enteredText) {
        return FXCollections.observableArrayList(word_en).filtered(word ->
                word.toLowerCase().startsWith(enteredText.toLowerCase())).toArray(new String[0]);
    }


    private String[] getFilteredWords(String enteredText) {
        return FXCollections.observableArrayList(words_fr).filtered(word ->
                word.toLowerCase().startsWith(enteredText.toLowerCase())).toArray(new String[0]);
    }
}
