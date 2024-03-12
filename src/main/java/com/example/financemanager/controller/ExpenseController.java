package com.example.financemanager.controller;

import com.example.financemanager.db.ExpenseDAO;
import com.example.financemanager.model.Expense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import org.slf4j.Logger;

import java.util.Optional;

public class ExpenseController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExpenseController.class);

    @FXML
    private TableView<Expense> expenseTable;

    public void initialize() {
        expenseTable.setItems(ExpenseDAO.getExpenses());
    }

    public void addExpense(ActionEvent event) {
        Dialog<Expense> addPersonDialog = new ExpenseDialog();
        Optional<Expense> result = addPersonDialog.showAndWait();
        result.ifPresent(ExpenseDAO::insertExpense);

        log.debug(result.toString());
        event.consume();
    }

}