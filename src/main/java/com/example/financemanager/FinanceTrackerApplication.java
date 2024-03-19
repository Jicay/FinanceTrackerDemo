package com.example.financemanager;

import com.example.financemanager.db.Database;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.slf4j.LoggerFactory.getLogger;

public class FinanceTrackerApplication extends Application {
    static {
        System.setProperty("app.root", findAndCreateOSFolder());
    }
    private static final Logger log = getLogger(FinanceTrackerApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        if (Database.isOK()) {
            FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("dashboard-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Finance Tracker");
            stage.getIcons().add(new Image(FinanceTrackerApplication.class.getResourceAsStream("assets/icon.png")));
            stage.setScene(scene);
            stage.show();
        } else {
            log.error("Could not load database");
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

    public static String findAndCreateOSFolder() {
        String workingDirectory;
        String OS = (System.getProperty("os.name")).toUpperCase();
        if (OS.contains("WIN")) {
            //it is simply the location of the "AppData" folder
            workingDirectory = System.getenv("AppData");
        } else {
            workingDirectory = System.getProperty("user.home");

            if (OS.contains("MAC")) {
                //if we are on a Mac, we are not done, we look for "Application Support"
                workingDirectory += "/Library/Application Support";
            }
        }
        workingDirectory += "/FinanceTracker";

        try {
            Files.createDirectory(Paths.get(workingDirectory));
        } catch (Exception e) {
            // do nothing
        }

        return workingDirectory;
    }
}