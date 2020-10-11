package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class OutflowItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "paycheck",
            "bonus", "investment"));

    public OutflowItem(double amount, LocalDate date) {
        this(amount, date, "uncategorized");
    }

    public OutflowItem(double amount, LocalDate date, String category) {
        super(amount, date, category);
    }

}
