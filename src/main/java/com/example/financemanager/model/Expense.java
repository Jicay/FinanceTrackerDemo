package com.example.financemanager.model;

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

    private final static String PRICE_FORMAT = "%.2f €";

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

    public String getDate() {
            return date.format(DATE_FORMAT);
    }

    public String getTotal() {
        return String.format(PRICE_FORMAT, total);
    }

    public String getHousing() {
        return String.format(PRICE_FORMAT, housing);
    }

    public String getFood() {
        return String.format(PRICE_FORMAT, food);
    }

    public String getGoingOut() {
        return String.format(PRICE_FORMAT, goingOut);
    }

    public String getTransportation() {
        return String.format(PRICE_FORMAT, transportation);
    }

    public String getTravel() {
        return String.format(PRICE_FORMAT, travel);
    }

    public String getTax() {
        return String.format(PRICE_FORMAT, tax);
    }

    public String getOther() {
        return String.format(PRICE_FORMAT, other);
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
}
