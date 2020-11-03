package ui;

import model.CashFlowAccount;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TablePanel extends JPanel {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final String JSON_PATH = "./data/itemList.json";
    JsonReader jsonReader;

    CashFlowAccount cashFlowAccount;

    public TablePanel() {
        super(new GridLayout(1, 0));
        initializeGlobal();
        addTable();
    }

    private void addTable() {
        JTable table = new JTable(new CustomTable(cashFlowAccount.getItemList()));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void initializeGlobal() {
        jsonReader = new JsonReader(JSON_PATH);
        cashFlowAccount = loadData();
    }

    private CashFlowAccount loadData() {
        try {
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Unable to read from file: " + JSON_PATH + ANSI_RESET);
        }
        return new CashFlowAccount();
    }
}
