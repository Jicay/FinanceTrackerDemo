package com.example.financemanager.controller;

import com.example.financemanager.FinanceTrackerApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class HeaderController {
    private static final Logger logger = getLogger(HeaderController.class);

    @FXML
    private Parent root;

    public void showDashboard(ActionEvent event) throws IOException {
        logger.info("showDashboard called");
        FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("dashboard-view.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        logger.info("showDashboard end");
    }

    public void showExpenses(ActionEvent event) throws IOException {
        logger.info("showExpenses called");
        FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("expense-view.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        logger.info("showExpenses end");
    }
}
