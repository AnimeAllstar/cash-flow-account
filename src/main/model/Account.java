package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final List<Item> cashFlowList = new ArrayList<>();
    private double goal;

    public Account(double goal) {
        this.goal = goal;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public List<Item> getCashFlowList() {
        return cashFlowList;
    }

    public List<Item> getCashFlowList(String filterBy) {
        ArrayList<Item> tempList = new ArrayList<>();
        for (Item elem : cashFlowList) {
            if (elem.getClassName().equals(filterBy)) {
                tempList.add(elem);
            }
        }
        return tempList;
    }

    public void addItem(Item item) {
        cashFlowList.add(item);
        Collections.sort(cashFlowList);
    }

    public boolean removeItem(int index) {
        if (index < cashFlowList.size() && index >= 0) {
            cashFlowList.remove(index);
            return true;
        }
        return false;
    }

}
