package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents an account that stores items created by the user
// into an ArrayList of type Item
// contains methods that allow the user to access and alter the list
public class CashFlowAccount implements Writable {
    private final List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    /*
     * REQUIRES: filterBy has to be equal to either "ExpenseItem" or "IncomeItem"
     * EFFECTS: creates an ArrayList of type Item called "tempList"
     *          for each element in itemList,
     *            - if the class of the element is equal to filterBy
     *                - add the element to tempList
     *          returns tempList
     */
    public List<Item> getItemList(String filterBy) {
        ArrayList<Item> tempList = new ArrayList<>();
        for (Item elem : itemList) {
            if (elem.getClassName().equals(filterBy)) {
                tempList.add(elem);
            }
        }
        return tempList;
    }

    public void sortItems() {
        Collections.sort(itemList);
    }

    public int size() {
        return itemList.size();
    }

    /*
     * REQUIRES: item must not be null
     * MODIFIES: this
     * EFFECTS: adds item to itemList
     *          sorts itemList
     */
    public void addItem(Item item) {
        itemList.add(item);
        Collections.sort(itemList);
    }

    /*
     * MODIFIES: this
     * EFFECTS: if itemList contains item
     *            - removes item from itemList
     *            - returns true
     *          otherwise, returns false
     */
    public boolean removeItem(Item item) {
        return itemList.remove(item);
    }

    public boolean removeItem(int index) {
        try {
            itemList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /*
     * EFFECTS: if itemList contains an item at index
     *            - returns item
     *          otherwise, returns null
     */
    public Item getItem(int index) {
        try {
            return itemList.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in CashFlowAccount as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item elem : itemList) {
            jsonArray.put(elem.toJson());
        }

        return jsonArray;
    }
}
