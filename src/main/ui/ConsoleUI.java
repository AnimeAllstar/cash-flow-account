package ui;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private CashFlowAccount cashFlowAccount;
    private Scanner sc;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public ConsoleUI() {
        boolean isExit = false;
        String choice;

        initializeGlobal();
        System.out.println("Welcome to your Cash Flow Account!");
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
        System.out.println("\nSee you soon!");
    }

    private void displayMenu() {
        System.out.println("| Display | (d) All Items | (d.in) Income Items | (d.exp) Expense Items |");
        System.out.println("| Add | (in) Income Item | (exp) Expense Item |");
        System.out.print("| (r) Remove | (m) Display Menu | (exit) Exit |\n");
    }


    @SuppressWarnings("checkstyle:MethodLength")
    private void select(String choice) {
        switch (choice) {
            case "d":
                displayRecords();
                break;
            case "d.in":
                displayRecords("IncomeItem");
                break;
            case "d.exp":
                displayRecords("ExpenseItem");
                break;
            case "in":
                addRecord(new IncomeItem());
                System.out.println(ANSI_BLUE + "Record Added!" + ANSI_RESET);
                break;
            case "exp":
                addRecord(new ExpenseItem());
                System.out.println(ANSI_BLUE + "Record Added!" + ANSI_RESET);
                break;
            case "e":
                editRecord(getRecord());
                break;
            case "r":
                removeRecord(getRecord());
                System.out.println(ANSI_BLUE + "Record Removed!" + ANSI_RESET);
                break;
            case "m":
                displayMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid selection" + ANSI_RESET);
        }
    }

    private void displayRecords() {
        displayRecords("");
    }

    private void displayRecords(String filterBy) {
        ArrayList<Item> records;
        if (filterBy.equals("")) {
            records = new ArrayList<>(cashFlowAccount.getItemList());
        } else {
            records = new ArrayList<>(cashFlowAccount.getItemList(filterBy));
        }

        int count = 1;
        for (Item elem : records) {
            System.out.println("[" + count + "] " + elem.toString());
            count++;
        }
    }

    private void addRecord(Item newItem) {
        System.out.print("Enter Label : ");
        sc.nextLine();
        newItem.setLabel(sc.nextLine());
        System.out.print("Enter Amount : $ ");
        newItem.setAmount(sc.nextDouble());
        System.out.print("Enter Date (yyyy-mm-dd) : ");
        newItem.setDate(LocalDate.parse(sc.next()));
        ArrayList<String> categories = printCategories(newItem.getClassName());
        System.out.print("Enter Category (use number) : ");
        setIndex(newItem, sc.nextInt(), categories);
        cashFlowAccount.addItem(newItem);
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

    private void setIndex(Item item, int index, ArrayList<String> categories) {
        while (!item.setCategory(index - 1, categories)) {
            System.out.println(ANSI_RED + "Invalid index. Please try again" + ANSI_RESET);
            System.out.print("Add Category (use number) : ");
            index = sc.nextInt();
        }
    }

    private void removeRecord(Item item) {
        cashFlowAccount.removeItem(item);
    }

    private Item getRecord() {
        displayRecords();
        boolean isValid = false;
        System.out.print("Enter index of row you wish to edit : ");
        int index = sc.nextInt();
        Item item = cashFlowAccount.getItem(index - 1);
        while (item == null) {
            System.out.println(ANSI_RED + "Invalid index. Please try again" + ANSI_RESET);
            System.out.print("Enter row index : ");
            index = sc.nextInt();
            item = cashFlowAccount.getItem(index - 1);
        }
        return item;
    }

    private void editRecord(Item item) {
        System.out.println("Selected Row: " + item.toString());
        removeRecord(item);
        addRecord(item);
        System.out.println(ANSI_BLUE + "Record Edited!" + ANSI_RESET);
    }

    private void initializeGlobal() {
        cashFlowAccount = new CashFlowAccount();
        sc = new Scanner(System.in);

        // Sample Data
        cashFlowAccount.addItem(new ExpenseItem("Item 1", 100, LocalDate.now(), "uncategorized"));
        cashFlowAccount.addItem(new IncomeItem("Item 2", 200, LocalDate.now(), "uncategorized"));
        cashFlowAccount.addItem(new ExpenseItem("Item 3", 300, LocalDate.now(), "uncategorized"));
        cashFlowAccount.addItem(new IncomeItem("Item 4", 10, LocalDate.now(), "uncategorized"));
        cashFlowAccount.addItem(new ExpenseItem("Item 5", 100, LocalDate.of(2020, Month.OCTOBER,
                20), "uncategorized"));
        cashFlowAccount.addItem(new IncomeItem("Item 6", 100, LocalDate.of(2020, Month.OCTOBER,
                1), "uncategorized"));
    }
}
