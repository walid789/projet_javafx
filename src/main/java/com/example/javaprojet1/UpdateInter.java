package com.example.javaprojet1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.sql.*;

public class UpdateInter extends Stage {
    int id=0;
    public UpdateInter(){

                // Create labels, textfields, and button
                Label label0 = new Label("the word in french");
                TextField textField0 = new TextField();
                Label label7 = new Label("the expression in french");
                TextField textField6 = new TextField();
                Button button0 = new Button("Search");

                Label label1 = new Label("Word In French :");
                Label label2 = new Label("Word In English :");
                Label label3 = new Label("Type :");
                Label label4 = new Label("Example in French :");
                Label label5 = new Label("Example in English :");
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();
                TextField textField3 = new TextField();
                TextField textField4 = new TextField();
                TextField textField5 = new TextField();
                Button button = new Button("Save");

                // Create vertical box and add labels, textfields, and button to it
                VBox vBox = new VBox();
                vBox.setPadding(new Insets(10));
                vBox.setSpacing(10);
                vBox.setAlignment(Pos.TOP_CENTER);
                vBox.getChildren().addAll(label0,textField0,label7,textField6,button0,label1, textField1, label2, textField2, label3, textField3, label4, textField4, label5, textField5, button);

        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    String req = "select * from dictionnaire where word_fr= ? and exmple_fr=?";

                    PreparedStatement stmt = connection.prepareStatement(req);
                    stmt.setString(1, textField0.getText());
                    stmt.setString(2, textField6.getText());
                    ResultSet rset = stmt.executeQuery();
                    while (rset.next()) {
                        textField1.setText(rset.getString(2));
                        id=rset.getInt(1);
                        textField2.setText(rset.getString(3));
                        textField3.setText(rset.getString(4));
                        textField4.setText(rset.getString(5));
                        textField5.setText(rset.getString(6));
                    }

                }
                catch (Exception e){
                    System.err.println(e);
                }
            }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    String sql = "update  dictionnaire set word_fr=? ,word_eng =?, type =? , exmple_fr=?,exmple_eng =? where id=?";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, textField1.getText());
                    stmt.setString(2,textField2.getText() );
                    stmt.setString(3, textField3.getText());
                    stmt.setString(4, textField4.getText());
                    stmt.setString(5, textField5.getText());
                    stmt.setInt(6, id);
                    int rows = stmt.executeUpdate();
                    AdminInterface ad=new AdminInterface();
                    close();
                    }
                catch (Exception e1) {
                    System.out.println(e1);
                }
        };
                           });
                // Create scene and set it on the stage
                Scene scene = new Scene(vBox, 400, 600);
                setScene(scene);
                setTitle("update interface");
                show();
            }


}

