package ui;

import model.BudgetManager;
import model.InflowItem;
import model.ListItem;
import model.OutflowItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private BudgetManager budgetManager;
    private Scanner sc;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private String choice;

    public ConsoleUI() {
        boolean isExit = false;

        initializeGlobal();
        System.out.println("Welcome to Budget Manager!");
        System.out.println("Instructions: Enter the value in the parenthesis to run commands");

        displayMenu();

        while (!isExit) {
            System.out.print(">> ");
            choice = sc.next().toLowerCase();

            if (choice.equals("exit")) {
                isExit = true;
            } else {
                select(choice);
            }
        }
        System.out.println("\nThank you for using Budget Manager!");
    }

    private void displayMenu() {
        System.out.println("| Display | \n\t| (d) All Items | (d.in) Inflow Items | (d.out) Outflow Items |");
        System.out.println("| Add | \n\t| (a.in) Inflow item | (a.out) Outflow Item |");
        System.out.print("| (r) Remove | (e) Edit | (exit) Exit |\n");
    }

    private void select(String choice) {
        switch (choice) {
            case "d":
                displayRecords("");
                break;
            case "d.in":
                displayRecords("InflowItem");
                break;
            case "d.out":
                displayRecords("OutflowItem");
                break;
            case "a.in":
                addRecord(new InflowItem());
                break;
            case "a.out":
                addRecord(new OutflowItem());
                break;
            case "r":
                removeRecord();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid selection" + ANSI_RESET);
                break;
        }
    }

    private void displayRecords(String filterBy) {
        ArrayList<ListItem> records;
        if (filterBy.equals("")) {
            records = new ArrayList<>(budgetManager.getCashFlowList());
        } else {
            records = new ArrayList<>(budgetManager.getCashFlowList(filterBy));
        }

        int count = 1;
        for (ListItem elem : records) {
            System.out.println("[" + count + "] " + elem.toString());
            count++;
        }
    }

    private void addRecord(ListItem newItem) {
        String label;
        double amount;
        int index;
        LocalDate date;
        System.out.print("Add Label : ");
        label = sc.next();
        System.out.print("Add Amount : ");
        amount = sc.nextDouble();
        System.out.print("Add Date : ");
        date = LocalDate.parse(sc.next());
        ArrayList<String> categories = printCategories(newItem.getClassName());
        System.out.print("Add Category (use number) : ");
        index = sc.nextInt();
        newItem.setLabel(label);
        newItem.setAmount(amount);
        newItem.setDate(date);
        setIndex(newItem, index, categories);
        budgetManager.addItem(newItem);
        System.out.println("Record Added!");
    }

    private ArrayList<String> printCategories(String className) {
        ArrayList<String> categories;
        if (className.equals("InflowItem")) {
            categories = InflowItem.categories;
        } else {
            categories = OutflowItem.categories;
        }
        int count = 1;
        for (String elem : categories) {
            System.out.print("[" + count + "]" + elem + " ");
            count++;
        }
        System.out.println();
        return categories;
    }

    private void setIndex(ListItem listItem, int index, ArrayList<String> categories) {
        while (!listItem.setCategory(index - 1, categories)) {
            System.out.println("Invalid index. Please try again");
            System.out.print("Add Category (use number) : ");
            index = sc.nextInt();
        }
    }

    private void removeRecord() {
    }

    private void initializeGlobal() {
        budgetManager = new BudgetManager(2000);
        sc = new Scanner(System.in);
        choice = null;
    }
}
