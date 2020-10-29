package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// represents an IncomeItem (subclass of Item)
// has a static variable called categories
// it stores a list of categories that any object of the class can have
public class IncomeItem extends Item {

    public static ArrayList<String> categories = new ArrayList<>(Arrays.asList(DEFAULT_CATEGORY, "paycheck",
            "bonus", "investment"));

    // zero-argument constructor
    // EFFECTS: passes default variables to this(String, double, LocalDate, String)
    public IncomeItem() {
        this(DEFAULT_LABEL, DEFAULT_AMOUNT, DEFAULT_DATE, DEFAULT_CATEGORY);
    }

    /*
     * REQUIRES: label has a non-zero length
     *           amount >= 0
     *           date in yyyy-mm-dd format
     *           category exists in IncomeItem.categories
     * EFFECTS: creates a new IncomeItem by initializing the variables in the superclass
     */
    public IncomeItem(String label, double amount, LocalDate date, String category) {
        super(label, amount, date, category);
    }

    @Override
    public String getClassName() {
        return "IncomeItem";
    }

}
