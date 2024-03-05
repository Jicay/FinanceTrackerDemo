package com.example.financemanager;

import com.example.financemanager.db.Database;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceTrackerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if (Database.isOK()) {
            FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("expense-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Finance Tracker");
            stage.getIcons().add(new Image(FinanceTrackerApplication.class.getResourceAsStream("assets/icon.png")));
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setHeaderText("Could not load database");
            alert.setContentText("Error loading SQLite database. See log. Quitting...");
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}