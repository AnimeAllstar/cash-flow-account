package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ListItem implements Comparable<ListItem> {

    private double amount;
    private LocalDate date;
    private String category;

    public ListItem(double amount, LocalDate date) {
        this(amount, date, "uncategorized");
    }

    public ListItem(double amount, LocalDate date, String category) {
        this.amount = amount;
        this.date = date;
        this.category = category.toLowerCase();
    }

    public abstract ArrayList<String> getCategories();

    public abstract void addCategory(String category);

    public abstract boolean removeCategory(String category);

    public int compareTo(ListItem o) {
        if (this.date.compareTo(o.date) != 0) {
            return this.date.compareTo(o.date);
        } else {
            if (this.amount - o.amount > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
