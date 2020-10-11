package ui;

import model.BudgetManager;

import java.util.Scanner;

public class ConsoleUI {

    private BudgetManager budgetManager;
    private Scanner sc;

    public ConsoleUI() {
        boolean isExit = false;
        String choice;

        initializeGlobal();
        System.out.println("Welcome to Budget Manager!");
        System.out.println("Instructions: Enter the value in the parenthesis to carry out the corresponding Task.");

        while (!isExit) {
            displayMenu();
            choice = sc.next().toLowerCase();

            if (choice.equals("exit")) {
                isExit = true;
            } else {
                //stub
            }
        }
    }

    private void displayMenu() {
        System.out.print("| (d) Display |");
        System.out.print(" (a) Add |");
        System.out.print(" (r) Remove |");
        System.out.print(" (h) Help |");
        System.out.print(" (exit) |\n");
        System.out.print(">> ");
    }

    private void initializeGlobal() {
        budgetManager = new BudgetManager(2000);
        sc = new Scanner(System.in);
    }
}
