package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BudgetManager {
    private final List<ListItem> cashFlowList = new ArrayList<>();
    private double budget;
    private double balance;
    private double warning;

    public BudgetManager(double balance) {
        this(balance, 0);
    }

    public BudgetManager(double balance, double budget) {
        this.balance = balance;
        this.budget = budget;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWarning() {
        return warning;
    }

    public void setWarning(double warning) {
        this.warning = warning;
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

    public void removeItem(int index) {
        cashFlowList.remove(index);
    }

    public void editItem(int index, ListItem item) {
        removeItem(index);
        addItem(item);
        Collections.sort(cashFlowList);
    }

}
