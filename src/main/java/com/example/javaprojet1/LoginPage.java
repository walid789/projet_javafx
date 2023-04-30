package com.example.javaprojet1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;



public class LoginPage extends Application {
    @Override
    public void start(Stage stage) {
        //creating label email
        Text text1 = new Text("Email");

        //creating label password
        Text text2 = new Text("Password");

        //Creating Text Filed for email
        TextField textField1 = new TextField();

        //Creating Text Filed for password
        TextField textField2 = new PasswordField();

        //Creating Buttons
        Button button1 = new Button("Submit");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username=textField1.getText();
                String password=textField2.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test", "root", ""
                    );
                    Statement statement = connection.createStatement();
                    String req = "select username,password from user where username= ? and password = ? ";
                    PreparedStatement stmt = connection.prepareStatement(req);
                    stmt.setString(1,username);
                    stmt.setString(2,password);
                    ResultSet resultSet =stmt.executeQuery();
                    String user="";
                    String pass="";
                    while (resultSet.next()) {
                        user=resultSet.getString(1);
                        pass=resultSet.getString(2);
                    }
                    System.out.println(user+pass);
                    if(user.equals("admin")){
                        AdminInterface ad =new AdminInterface();
                        ad.show();
                        stage.close();
                    }

                    else if(user==""){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("mots du pass or usenrame incorrect");
                        alert.showAndWait();
                        System.out.println("mots du pass or usenrame incorrect");
                    }
                    else {
                     userinter in=new userinter();
                        in.show();
                        stage.close();
                    }
                }
                catch (Exception e){
                    System.err.println(e);
                }

            }
        });
        Button button2 = new Button("Clear");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

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

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("login in");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}