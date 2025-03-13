package com.example.financemanager.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private final LocalDate date;
    private final float total;
    private final float housing;
    private final float food;
    private final float goingOut;
    private final float transportation;
    private final float travel;
    private final float tax;
    private final float other;
    private boolean isDollar = false;
    private float changeRate = 1.0f;

    private final static String PRICE_FORMAT = "%.2f €";
    private final static String DOLLAR_PRICE_FORMAT = "$%.2f";

    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM yyyy");

    public Expense(LocalDate date, float housing, float food, float goingOut, float transportation, float travel, float tax, float other) {
        this.date = date;
        this.total = housing + food + goingOut + transportation + travel + tax + other;
        this.housing = housing;
        this.food = food;
        this.goingOut = goingOut;
        this.transportation = transportation;
        this.travel = travel;
        this.tax = tax;
        this.other = other;
    }

    public StringProperty dateProperty() {
        return new SimpleStringProperty(date.format(DATE_FORMAT));
    }

    public StringProperty totalProperty() {
        return formatAmount(total);
    }

    private SimpleStringProperty formatAmount(float amount) {
        if (isDollar) {
            return new SimpleStringProperty(String.format(DOLLAR_PRICE_FORMAT, amount*changeRate));
        }
        return new SimpleStringProperty(String.format(PRICE_FORMAT, amount));
    }

    public StringProperty housingProperty() {
        return formatAmount(housing);
    }

    public StringProperty foodProperty() {
        return formatAmount(food);
    }

    public StringProperty goingOutProperty() {
        return formatAmount(goingOut);
    }

    public StringProperty transportationProperty() {
        return formatAmount(transportation);
    }

    public StringProperty travelProperty() {
        return formatAmount(travel);
    }

    public StringProperty taxProperty() {
        return formatAmount(tax);
    }

    public StringProperty otherProperty() {
        return formatAmount(other);
    }

    public LocalDate getDate() {
        return date;
    }

    public float getTotal() {
        return total;
    }

    public float getHousing() {
        return housing;
    }

    public float getFood() {
        return food;
    }

    public float getGoingOut() {
        return goingOut;
    }

    public float getTransportation() {
        return transportation;
    }

    public float getTravel() {
        return travel;
    }

    public float getTax() {
        return tax;
    }

    public float getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "date=" + date +
                ", total=" + total +
                ", housing=" + housing +
                ", food=" + food +
                ", goingOut=" + goingOut +
                ", transportation=" + transportation +
                ", travel=" + travel +
                ", tax=" + tax +
                ", other=" + other +
                '}';
    }

    public int compareTo(Expense expense) {
        return -this.date.compareTo(expense.date);
    }

    public void switchCurrency(float changeRate) {
        isDollar = !isDollar;
        this.changeRate = changeRate;
    }
}
