package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class OutflowItem extends ListItem {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList("uncategorized", "paycheck",
            "bonus", "investment"));

    public OutflowItem() {
        this("label", 0, LocalDate.now(), "uncategorized");
    }

    public OutflowItem(String label, double amount, LocalDate date) {
        this(label, amount, date, "uncategorized");
    }

    public OutflowItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "OutflowItem";
    }

}
