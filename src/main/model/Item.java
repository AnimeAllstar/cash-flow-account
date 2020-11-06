package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

// represents an Item having an label, date, category and amount (in dollars)
public abstract class Item implements Comparable<Item>, Writable {

    // default values for variables below (used by zero-argument constructors and objects in sub-classes and tests)
    protected static final String DEFAULT_CATEGORY = "uncategorized";
    protected static final LocalDate DEFAULT_DATE = LocalDate.now();
    protected static final String DEFAULT_LABEL = "New Item";
    protected static final double DEFAULT_AMOUNT = 0;

    private String label;               // description of the Item
    private double amount;              // value of Item (in dollars)
    private LocalDate date;             // date of transaction for the Item (format: yyyy-mm-dd)
    private String category;            // stores the category of the Item (eg: "Healthcare" or "Education")
                                        // objects can only have one category

    /*
     * REQUIRES: label has a non-zero length
     *           amount >= 0
     *           date in yyyy-MM-dd format
     *           category exists in "categories" variable in sub-class
     * EFFECTS: creates a new Item with a label, amount, date and category
     */
    public Item(String label, double amount, LocalDate date, String category) {
        this.label = label;
        this.amount = amount;
        this.date = date;
        this.category = category.toLowerCase();
    }

    /*
     * implementation of compareTo(T o) in "Comparable" interface
     * provides sorting sequence by comparing variables of the current object and the object "o"
     * EFFECTS: if o.date != this.date
     *            - return o.date - this.date -> primary sorting criteria using dates (descending)
     *          else
     *            - return o.amount - this.amount -> secondary sorting criteria using amount if dates
     *                                               are the same (descending)
     */
    public int compareTo(Item o) {
        if (o.date.compareTo(this.date) != 0) {
            return (o.date.compareTo(this.date));
        } else {
            double temp = o.amount - this.amount;
            return (int) ((temp > 0) ? Math.ceil(temp) : Math.floor(temp));
        }
    }

    public abstract String getClassName();

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateString() {
        return String.valueOf(date);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if index is not negative and less than categories.size()
     *            - sets this.category to categories.get(index)
     *            - return true
     *          otherwise, returns false
     */
    public boolean setCategory(int index, ArrayList<String> categories) {
        if (index >= 0 && categories.size() > index) {
            this.category = categories.get(index);
            return true;
        } else {
            return false;
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%-20s $ %-15.2f %-12s %-15s", label, amount, date.toString(), category);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", getClassName());
        json.put("label", getLabel());
        json.put("amount", getAmount());
        json.put("date", getDate());
        json.put("category", getCategory());
        return json;
    }
}
