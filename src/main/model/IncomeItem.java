package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class IncomeItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "entertainment",
            "education", "shopping", "food and dining", "healthcare", "bills", "transport", "travel"));

    public IncomeItem() {
        this("label", 0, LocalDate.now(), "uncategorized");
    }

    public IncomeItem(String label, double amount, LocalDate date) {
        this(label, amount, date, "uncategorized");
    }

    public IncomeItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "IncomeItem";
    }

}
