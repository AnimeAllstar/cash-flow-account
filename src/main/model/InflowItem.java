package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class InflowItem extends ListItem {

    private static final ArrayList<String> categories = (ArrayList<String>) Arrays.asList("uncategorized",
            "entertainment", "education", "shopping", "food and dining", "healthcare", "bills", "transport", "travel");

    public InflowItem(double amount, LocalDate date) {
        this(amount, date, "uncategorized");
    }

    public InflowItem(double amount, LocalDate date, String category) {
        super(amount, date, category);
    }

    @Override
    public ArrayList<String> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(String category) {
        categories.add(category);
    }

    @Override
    public boolean removeCategory(String category) {
        return categories.remove(category);
    }

}
