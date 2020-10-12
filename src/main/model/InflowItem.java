package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class InflowItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "entertainment",
            "education", "shopping", "food and dining", "healthcare", "bills", "transport", "travel"));

    public InflowItem() {
        this("label", 0, LocalDate.now(), "uncategorized");
    }

    public InflowItem(String label, double amount, LocalDate date) {
        this(label, amount, date, "uncategorized");
    }

    public InflowItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "InflowItem";
    }

}
