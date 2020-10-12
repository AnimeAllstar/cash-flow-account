package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Item implements Comparable<Item> {

    private String label;
    private double amount;
    private LocalDate date;
    private String category;

    public Item(String label, double amount, LocalDate date, String category) {
        this.label = label;
        this.amount = amount;
        this.date = date;
        this.category = category.toLowerCase();
    }

    public int compareTo(Item o) {
        if (this.date.compareTo(o.date) != 0) {
            return -(this.date.compareTo(o.date));
        } else {
            if (this.amount - o.amount > 0) {
                return -1;
            } else if (this.amount - o.amount < 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public abstract String getClassName();

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

    public boolean setCategory(int index, ArrayList<String> categories) {
        if (index >= 0 && categories.size() > index) {
            this.category = categories.get(index);
            return true;
        } else {
            return false;
        }
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%-20s $ %-15.2f %-12s %-15s", label, amount, date.toString(), category);
    }
}
