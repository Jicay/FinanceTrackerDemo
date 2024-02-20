package com.example.financemanager.controller;

import com.example.financemanager.model.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Optional;

public class ExpenseController {
    @FXML
    private TableView<Expense> expenseTable;

    private final ObservableList<Expense> items = FXCollections.observableArrayList();

    public void initialize() {
        items.addAll(
                new Expense(LocalDate.of(2023, 1, 1), 600.0f, 200.0f, 150.32f, 100.0f, 426.0f, 200.0f, 92.0f),
                new Expense(LocalDate.of(2023, 2, 1), 600.0f, 233.23f, 150.32f, 100.0f, 426.0f, 200.0f, 92.0f),
                new Expense(LocalDate.of(2023, 3, 1), 600.0f, 200.0f, 150.32f, 130.0f, 426.0f, 200.0f, 92.0f)
        );
        expenseTable.setItems(items);
    }

    public void addExpense(ActionEvent event) {
        Dialog<Expense> addPersonDialog = new ExpenseDialog();
        Optional<Expense> result = addPersonDialog.showAndWait();
        result.ifPresent(items::add);

        System.out.println(result);
        event.consume();
    }

}