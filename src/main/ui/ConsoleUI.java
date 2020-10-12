package ui;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// represents a Console-based User Interface
public class ConsoleUI {

    //ANSI escape codes used to improve user experience by adding colours where appropriate
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private CashFlowAccount cashFlowAccount;
    private Scanner sc;

    // EFFECTS: runs the ConsoleUI
    public ConsoleUI() {
        runConsole();
    }

    // adapted from TellerApp
    // MODIFIES: this
    // EFFECTS: accepts user input
    public void runConsole() {
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

    // MODIFIES: this
    // EFFECTS: initializes global variables
    private void initializeGlobal() {
        cashFlowAccount = new CashFlowAccount();
        sc = new Scanner(System.in);
    }

    // EFFECTS: displays a list of possible commands to the user
    private void displayMenu() {
        System.out.println("| (d) Display All Items | (d.in) Display Income Items | (d.exp) Display Expense Items |");
        System.out.println("| (in) Add Income Item | (exp) Add Expense Item |");
        System.out.print("| (r) Remove | (m) Display Menu | (exit) Exit |\n");
    }


    // MODIFIES: this
    // EFFECTS: processes user input
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

    /*
     * REQUIRES: filterBy is equal to either "" or "ExpenseItem" or "IncomeItem"
     * EFFECTS: if filterBy is equal to ""
     *            - records =  all elements in cashFlowAccount.itemList
     *          else
     *            - records = elements of either "ExpenseItem" or "IncomeItem"
     *          print every element in records
     */
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

    // REQUIRES: newItem cannot be null
    // MODIFIES: this
    // EFFECTS: accepts user input for new Item
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

    // REQUIRES: className must be "ExpenseItem" or "IncomeItem"
    // EFFECTS: initializes the local variable categories to the categories ArrayList in
    //          either the ExpenseItem or IncomeItem class (depending on className)
    //          prints every element in categories
    //          returns categories
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

    // REQUIRES: item is not null
    //           categories is not null
    // EFFECTS: Contains a while loop to ensure that the user enters an index
    //          within the bounds of the categories ArrayList
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

    // EFFECTS: displays all records using this.displayRecords()
    //          returns the Item at the index inputted by the user
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

    // REQUIRES: item is not null
    // MODIFIES: this
    // EFFECTS: displays details of item
    //          removes item from the itemList using this.removeRecord(item)
    //          adds a new item to the itemList using this.addRecord(item)
    private void editRecord(Item item) {
        System.out.println("Selected Row: " + item.toString());
        removeRecord(item);
        addRecord(item);
        System.out.println(ANSI_BLUE + "Record Edited!" + ANSI_RESET);
    }

}
