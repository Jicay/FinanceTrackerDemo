module com.example.financemanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.financemanager to javafx.fxml;
    exports com.example.financemanager;
    exports com.example.financemanager.model;
    exports com.example.financemanager.controller;
    opens com.example.financemanager.controller to javafx.fxml;
}