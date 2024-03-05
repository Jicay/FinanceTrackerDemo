package com.example.financemanager.db;

import com.example.financemanager.model.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseDAO {

    private static final String tableName = "expense";
    private static final String dateColumn = "date";
    private static final String housingColumn = "housing";
    private static final String foodColumn = "food";
    private static final String goingOutColumn = "goingOut";
    private static final String transportationColumn = "transportation";
    private static final String travelColumn = "travel";
    private static final String taxColumn = "tax";
    private static final String otherColumn = "other";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ObservableList<Expense> expenses;

    static {
        expenses = FXCollections.observableArrayList();
        fetchAllDataFromDB();
    }

    public static ObservableList<Expense> getExpenses() {
        return FXCollections.unmodifiableObservableList(expenses.sorted(Expense::compareTo));
    }

    private static void fetchAllDataFromDB() {

        String query = "SELECT * FROM " + tableName;

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            expenses.clear();
            while (rs.next()) {
                expenses.add(new Expense(
                        LocalDate.parse(rs.getString(dateColumn), DATE_FORMAT),
                        rs.getFloat(housingColumn),
                        rs.getFloat(foodColumn),
                        rs.getFloat(goingOutColumn),
                        rs.getFloat(transportationColumn),
                        rs.getFloat(travelColumn),
                        rs.getFloat(taxColumn),
                        rs.getFloat(otherColumn)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            expenses.clear();
        }
    }

    public static void insertExpense(Expense expense) {
        //update database
        String query = "INSERT INTO expense(date, housing, food, goingOut, transportation, travel, tax, other) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, expense.getDate().format(DATE_FORMAT));
            statement.setFloat(2, expense.getHousing());
            statement.setFloat(3, expense.getFood());
            statement.setFloat(4, expense.getGoingOut());
            statement.setFloat(5, expense.getTransportation());
            statement.setFloat(6, expense.getTravel());
            statement.setFloat(7, expense.getTax());
            statement.setFloat(8, expense.getOther());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update cache
        expenses.add(expense);
    }

}