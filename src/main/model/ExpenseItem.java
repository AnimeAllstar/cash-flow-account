package model;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// represents an ExpenseItem (subclass of Item)
// has a static variable called categories
// it stores a list of categories that any object of the class can have
public class ExpenseItem extends Item {

    public static final ArrayList<String> CATEGORIES = new ArrayList<>(Arrays.asList(DEFAULT_CATEGORY, "entertainment",
            "education", "shopping", "food and dining", "healthcare", "bills", "transport", "travel"));

    public static final String CLASS_NAME = "ExpenseItem";

    // zero-argument constructor
    // EFFECTS: passes default variables to this(String, double, LocalDate, String)
    public ExpenseItem() {
        this(DEFAULT_DESCRIPTION, DEFAULT_AMOUNT, DEFAULT_DATE, DEFAULT_CATEGORY);
    }

    /*
     * REQUIRES: description has a non-zero length
     *           amount >= 0
     *           date in yyyy-mm-dd format
     *           category exists in ExpenseItem.categories
     * EFFECTS: creates a new ExpenseItem by initializing the variables in the superclass
     */
    public ExpenseItem(String description, double amount, LocalDate date, String category) {
        super(description, amount, date, category);
    }

    @Override
    public JSONObject toJson(String type) {
        return super.toJson(CLASS_NAME);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
