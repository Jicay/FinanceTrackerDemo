module com.example.financemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires kotlin.stdlib;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.financemanager to javafx.fxml;
    exports com.example.financemanager;
    exports com.example.financemanager.model;
    exports com.example.financemanager.controller;
    exports com.example.financemanager.component;
    exports com.example.financemanager.api;
    opens com.example.financemanager.controller to javafx.fxml;
}