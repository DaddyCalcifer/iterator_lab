module com.example.iterator_lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.iterator_lab to javafx.fxml;
    exports com.example.iterator_lab;
}