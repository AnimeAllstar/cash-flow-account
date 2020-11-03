package ui;

import model.CashFlowAccount;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainPanel extends JPanel {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final String JSON_PATH = "./data/itemList.json";
    JsonReader jsonReader;
    JsonWriter jsonWriter;

    JTable table;
    CashFlowAccount cashFlowAccount;

    public MainPanel() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 800));
        initializeGlobal();
        addTable();
        //addSouthPanel();
    }

//    private void addSouthPanel() {
//        Panel buttonPanel = new Panel(new FlowLayout());
//        buttonPanel.setPreferredSize(new Dimension(this.getWidth(),100));
//    }

    public void revertChanges() {
        table.setModel(new CustomTable(loadData().getItemList()));
    }

    public void saveChanges() {
        cashFlowAccount.replaceItems(((CustomTable) table.getModel()).getData());
        updateData();
    }

    private void addTable() {
        table = new JTable(new CustomTable(cashFlowAccount.getItemList()));
        table.setPreferredScrollableViewportSize(new Dimension(500, 800));
        table.setFillsViewportHeight(true);
        table.setRowHeight(50);
        table.setRowHeight(0, 50);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.NORTH);
    }

    private void initializeGlobal() {
        jsonReader = new JsonReader(JSON_PATH);
        jsonWriter = new JsonWriter(JSON_PATH);
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

    private void updateData() {
        try {
            jsonWriter.write(cashFlowAccount);
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Unable to write to file: " + JSON_PATH + ANSI_RESET);
        }
    }
}
