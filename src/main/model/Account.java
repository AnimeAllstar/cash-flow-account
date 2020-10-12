package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final List<ListItem> cashFlowList = new ArrayList<>();
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

    public List<ListItem> getCashFlowList() {
        return cashFlowList;
    }

    public List<ListItem> getCashFlowList(String filterBy) {
        ArrayList<ListItem> tempList = new ArrayList<>();
        for (ListItem elem : cashFlowList) {
            if (elem.getClassName().equals(filterBy)) {
                tempList.add(elem);
            }
        }
        return tempList;
    }

    public void addItem(ListItem item) {
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
