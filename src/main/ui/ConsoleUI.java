package ui;

import model.Account;
import model.ExpenseItem;
import model.IncomeItem;
import model.ListItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private Account account;
    private Scanner sc;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public ConsoleUI() {
        boolean isExit = false;
        String choice;

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
        System.out.println("| Display | (d) All Items | (d.in) Income Items | (d.out) Expense Items |");
        System.out.println("| Add | (a.in) Income Item | (a.exp) Expense Item |");
        System.out.print("| (r) Remove | (g) Goals | (m) This Menu | (exit) Exit |\n");
    }

    private void select(String choice) {
        switch (choice) {
            case "d":
                displayRecords("");
                break;
            case "d.in":
                displayRecords("IncomeItem");
                break;
            case "d.out":
                displayRecords("ExpenseItem");
                break;
            case "a.in":
                addRecord(new IncomeItem());
                break;
            case "a.exp":
                addRecord(new ExpenseItem());
                break;
            case "r":
                removeRecord();
                break;
            case "m":
                displayMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid selection" + ANSI_RESET);
        }
    }

    private void displayRecords(String filterBy) {
        ArrayList<ListItem> records;
        if (filterBy.equals("")) {
            records = new ArrayList<>(account.getCashFlowList());
        } else {
            records = new ArrayList<>(account.getCashFlowList(filterBy));
        }

        int count = 1;
        for (ListItem elem : records) {
            System.out.println("[" + count + "] " + elem.toString());
            count++;
        }
    }

    private void addRecord(ListItem newItem) {
        System.out.print("Add Label : ");
        newItem.setLabel(sc.next());
        System.out.print("Add Amount : $ ");
        newItem.setAmount(sc.nextDouble());
        System.out.print("Add Date (yyyy-mm-dd) : ");
        newItem.setDate(LocalDate.parse(sc.next()));
        ArrayList<String> categories = printCategories(newItem.getClassName());
        System.out.print("Add Category (use number) : ");
        setIndex(newItem, sc.nextInt(), categories);
        account.addItem(newItem);
        System.out.println(ANSI_BLUE + "Record Added!" + ANSI_RESET);
    }

    private ArrayList<String> printCategories(String className) {
        ArrayList<String> categories;
        if (className.equals("IncomeItem")) {
            categories = IncomeItem.categories;
        } else {
            categories = ExpenseItem.categories;
        }
        int count = 1;
        for (String elem : categories) {
            System.out.print("[" + count + "] " + elem + " ");
            count++;
        }
        System.out.println();
        return categories;
    }

    private void setIndex(ListItem listItem, int index, ArrayList<String> categories) {
        while (!listItem.setCategory(index - 1, categories)) {
            System.out.println(ANSI_RED + "Invalid index. Please try again" + ANSI_RESET);
            System.out.print("Add Category (use number) : ");
            index = sc.nextInt();
        }
    }

    private void removeRecord() {
        displayRecords("");
        boolean isValid = false;
        System.out.print("Enter index of row you wish to remove : ");
        int index = sc.nextInt();
        while (!account.removeItem(index - 1)) {
            System.out.println(ANSI_RED + "Invalid index. Please try again" + ANSI_RESET);
            System.out.print("Enter row index : ");
            index = sc.nextInt();
        }
    }

    private void initializeGlobal() {
        account = new Account(2000);
        sc = new Scanner(System.in);

        // Sample Data
        account.addItem(new ExpenseItem("Item 1", 100, LocalDate.now(), "uncategorized"));
        account.addItem(new IncomeItem("Item 2", 200, LocalDate.now(), "uncategorized"));
        account.addItem(new ExpenseItem("Item 3", 300, LocalDate.now(), "uncategorized"));
        account.addItem(new IncomeItem("Item 4", 10, LocalDate.now(), "uncategorized"));
        account.addItem(new ExpenseItem("Item 5", 100, LocalDate.of(2020, Month.OCTOBER,
                20), "uncategorized"));
        account.addItem(new IncomeItem("Item 6", 100, LocalDate.of(2020, Month.OCTOBER,
                1), "uncategorized"));
    }
}
