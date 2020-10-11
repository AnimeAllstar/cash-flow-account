package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class InflowItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "entertainment",
            "education", "shopping", "food and dining", "healthcare", "bills", "transport", "travel"));

    public InflowItem(double amount, LocalDate date) {
        this(amount, date, "uncategorized");
    }

    public InflowItem(double amount, LocalDate date, String category) {
        super(amount, date, category);
    }

}
