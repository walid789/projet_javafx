module com.example.javaprojet1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javaprojet1 to javafx.fxml;
    exports com.example.javaprojet1;
}