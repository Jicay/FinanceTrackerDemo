package com.example.financemanager.model;

public class Expense {
    private String date;
    private float total;
    private float housing;
    private float food;
    private float goingOut;
    private float transportation;
    private float travel;
    private float tax;
    private float other;

    public Expense() {
        this("", 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Expense(String date, float total, float housing, float food, float goingOut, float transportation, float travel, float tax, float other) {
        this.date = date;
        this.total = total;
        this.housing = housing;
        this.food = food;
        this.goingOut = goingOut;
        this.transportation = transportation;
        this.travel = travel;
        this.tax = tax;
        this.other = other;
    }

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setHousing(float housing) {
        this.housing = housing;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public void setGoingOut(float goingOut) {
        this.goingOut = goingOut;
    }

    public void setTransportation(float transportation) {
        this.transportation = transportation;
    }

    public void setTravel(float travel) {
        this.travel = travel;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public void setOther(float other) {
        this.other = other;
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
