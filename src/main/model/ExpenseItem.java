package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpenseItem extends Item {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList(DEFAULT_CATEGORY, "paycheck",
            "bonus", "investment"));

    public ExpenseItem() {
        this(DEFAULT_LABEL, DEFAULT_AMOUNT, DEFAULT_DATE, DEFAULT_CATEGORY);
    }

    public ExpenseItem(String label, double amount, LocalDate date) {
        this(label, amount, date, DEFAULT_CATEGORY);
    }

    public ExpenseItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "ExpenseItem";
    }

}
