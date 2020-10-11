package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ListItem implements Comparable<ListItem> {

    private double amount;
    private LocalDate date;
    private String category;

    public ListItem(double amount, LocalDate date, String category) {
        this.amount = amount;
        this.date = date;
        this.category = category.toLowerCase();
    }

    public int compareTo(ListItem o) {
        if (this.date.compareTo(o.date) != 0) {
            return this.date.compareTo(o.date);
        } else {
            if (this.amount - o.amount > 0) {
                return 1;
            } else if (this.amount - o.amount < 0) {
                return -1;
            } else {
                return 0;
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

    public boolean setCategory(String category, ArrayList<String> categories) {
        if (categories.contains(category)) {
            this.category = category;
            return true;
        } else {
            return false;
        }
    }

}
