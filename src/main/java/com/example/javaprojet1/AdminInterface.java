package com.example.javaprojet1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class AdminInterface extends Stage {


public AdminInterface() {

    Button button1 = new Button("ADD TRADITION");
    button1.setOnAction(e->add());
    Button button2 = new Button("DELETE TRADITION");
    button2.setOnAction(e->delete());
    Button button3 = new Button("UPDATE TRADITION");
    button3.setOnAction(e->update());
    Button button4 = new Button("EXPORT DATA");
    button4.setOnAction(e->export());
    Button button5 = new Button("IMPORT  DATA");
    button5.setOnAction(e->import1());

    VBox layout = new VBox(button1, button2, button3, button4, button5);
    Scene scene = new Scene(layout, 400, 400);

    Stage primaryStage;
    setTitle("JavaFX Interface with 5 Buttons");
    setScene(scene);
    show();
}
    public void add(){
        AddInterface add =new AddInterface();
        add.show();
        close();
    }
    public void delete(){
        Delete add =new Delete();
        add.show();
        close();
    }
    public void update(){
        UpdateInter add =new UpdateInter();
        add.show();
        close();
    }
    public void export(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", ""
            );
            Statement statement = connection.createStatement();
            String req = "select * from dictionnaire ";
            PreparedStatement stmt = connection.prepareStatement(req);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Dictionnaire> list = new ArrayList<Dictionnaire>();
            while (resultSet.next()) {
                list.add(new Dictionnaire(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6))
                );

            }
            FileOutputStream fos = new FileOutputStream("dictionnaire.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println(list);
            oos.writeObject(list);
            oos.close();
            fos.close();

        } catch (Exception e) {
            System.err.println(e);
        }

    }
    public void import1(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", ""
            );
            String sql = "INSERT INTO dictionnaire (word_fr ,word_eng,type,exmple_fr,exmple_eng) VALUES (?, ?,?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            FileInputStream fis = new FileInputStream("dictionnaire.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Dictionnaire> listes = (ArrayList<Dictionnaire>) ois.readObject();
            int rows=0;
            for (Dictionnaire item : listes) {
                stmt.setString(1,item.word_fr);
                stmt.setString(2,item.word_eng );
                stmt.setString(3, item.type);
                stmt.setString(4, item.exmple_fr);
                stmt.setString(5, item.exmple_eng);
                rows= stmt.executeUpdate()+1;
            }

        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
    public static void main(String[] args) {
    }
}
