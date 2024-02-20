package com.example.financemanager.controller;

import com.example.financemanager.FinanceTrackerApplication;
import com.example.financemanager.model.Expense;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ExpenseDialog extends Dialog<Expense> {
    @FXML
    private TextField dateField;

    @FXML
    private TextField housingField;

    @FXML
    private TextField foodField;

    @FXML
    private TextField goingOutField;

    @FXML
    private TextField transportationField;

    @FXML
    private TextField travelField;

    @FXML
    private TextField taxField;

    @FXML
    private TextField otherField;

    @FXML
    private ButtonType createButton;

    public ExpenseDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(FinanceTrackerApplication.class.getResource("expense-dialog.fxml"));
            loader.setController(this);

            DialogPane dialogPane = loader.load();

            setTitle("Ajouter des dépenses");
            setDialogPane(dialogPane);
            initResultConverter();

            // Disable button when all field are not filled
            computeIfButtonIsDisabled();

            // Ensure only numeric input are set in the fields
            forceDoubleFormat();

            forceDateFormat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void forceDateFormat() {
        UnaryOperator<TextFormatter.Change> dateValidationFormatter = t -> {
            if (t.isAdded()) {
                if (t.getControlText().length() > 8) {
                    t.setText("");
                } else if (t.getControlText().matches(".*[0-9]{2}")) {
                    if (t.getText().matches("[^/]")) {
                        t.setText("");
                    }
                } else if (t.getText().matches("[^0-9]")) {
                    t.setText("");
                }
            }
            return t;
        };
        dateField.setTextFormatter(new TextFormatter<>(dateValidationFormatter));
    }

    private void forceDoubleFormat() {
        UnaryOperator<TextFormatter.Change> numberValidationFormatter = t -> {
            if (t.isReplaced())
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));


            if (t.isAdded()) {
                if (t.getControlText().contains(".")) {
                    if (t.getText().matches("[^0-9]")) {
                        t.setText("");
                    }
                } else if (t.getText().matches("[^0-9.]")) {
                    t.setText("");
                }
            }
            return t;
        };
        housingField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        foodField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        goingOutField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        transportationField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        travelField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        taxField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
        otherField.setTextFormatter(new TextFormatter<>(numberValidationFormatter));
    }

    private void computeIfButtonIsDisabled() {
        getDialogPane().lookupButton(createButton).disableProperty().bind(
                Bindings.createBooleanBinding(() -> dateField.getText().trim().isEmpty(), dateField.textProperty())
                        .or(Bindings.createBooleanBinding(() -> housingField.getText().trim().isEmpty(), housingField.textProperty())
                                .or(Bindings.createBooleanBinding(() -> foodField.getText().trim().isEmpty(), foodField.textProperty())
                                        .or(Bindings.createBooleanBinding(() -> goingOutField.getText().trim().isEmpty(), goingOutField.textProperty())
                                                .or(Bindings.createBooleanBinding(() -> transportationField.getText().trim().isEmpty(), transportationField.textProperty())
                                                        .or(Bindings.createBooleanBinding(() -> travelField.getText().trim().isEmpty(), travelField.textProperty())
                                                                .or(Bindings.createBooleanBinding(() -> taxField.getText().trim().isEmpty(), taxField.textProperty())
                                                                        .or(Bindings.createBooleanBinding(() -> otherField.getText().trim().isEmpty(), otherField.textProperty())
                                                                        ))))
                                        ))));
    }

    private void initResultConverter() {
        setResultConverter(buttonType -> {
            if (!Objects.equals(ButtonBar.ButtonData.OK_DONE, buttonType.getButtonData())) {
                return null;
            }

            return new Expense(
                    LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yy")),
                    Float.parseFloat(housingField.getText()),
                    Float.parseFloat(foodField.getText()),
                    Float.parseFloat(goingOutField.getText()),
                    Float.parseFloat(transportationField.getText()),
                    Float.parseFloat(travelField.getText()),
                    Float.parseFloat(taxField.getText()),
                    Float.parseFloat(otherField.getText())
            );
        });
    }

    @FXML
    private void initialize() {

    }
}