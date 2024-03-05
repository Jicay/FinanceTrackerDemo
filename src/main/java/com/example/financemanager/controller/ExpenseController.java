package com.example.financemanager.controller;

import com.example.financemanager.db.ExpenseDAO;
import com.example.financemanager.model.Expense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

import java.util.Optional;

public class ExpenseController {
    @FXML
    private TableView<Expense> expenseTable;

    public void initialize() {
        expenseTable.setItems(ExpenseDAO.getExpenses());
    }

    public void addExpense(ActionEvent event) {
        Dialog<Expense> addPersonDialog = new ExpenseDialog();
        Optional<Expense> result = addPersonDialog.showAndWait();
        result.ifPresent(ExpenseDAO::insertExpense);

        System.out.println(result);
        event.consume();
    }

}