package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CashFlowAccount {
    private final List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    public List<Item> getItemList(String filterBy) {
        ArrayList<Item> tempList = new ArrayList<>();
        for (Item elem : itemList) {
            if (elem.getClassName().equals(filterBy)) {
                tempList.add(elem);
            }
        }
        return tempList;
    }

    public void addItem(Item item) {
        itemList.add(item);
        Collections.sort(itemList);
    }

    public boolean removeItem(Item item) {
        return itemList.remove(item);
    }

    public Item getItem(int index) {
        try {
            return itemList.get(index);
        } catch (Exception e) {
            return null;
        }
    }
}
