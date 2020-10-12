package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpenseItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "paycheck",
            "bonus", "investment"));

    public ExpenseItem() {
        this("label", 0, LocalDate.now(), "uncategorized");
    }

    public ExpenseItem(String label, double amount, LocalDate date) {
        this(label, amount, date, "uncategorized");
    }

    public ExpenseItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "ExpenseItem";
    }

}