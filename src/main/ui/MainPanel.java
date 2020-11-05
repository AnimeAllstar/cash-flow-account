package ui;

import model.CashFlowAccount;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainPanel extends JPanel implements ActionListener {

    private static final String JSON_PATH = "./data/itemList.json";
    protected JTable table;
    protected JPopupMenu rightClickMenu;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public MainPanel() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 800));
        initializeGlobal();
        configureTable();
        addToRightClickMenu();
    }

    private void addToRightClickMenu() {
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.setActionCommand("remove");
        removeItem.addActionListener(this);
        rightClickMenu.add(removeItem);
        table.setComponentPopupMenu(rightClickMenu);
    }

    public void revertChanges() {
        table.setModel(new TableModel(loadData()));
        alignTableContents(SwingConstants.CENTER);
    }

    public void saveChanges() {
        updateData(((TableModel) table.getModel()).getCashFlowAccount());
    }

    private void configureTable() {
        table.setFillsViewportHeight(true);
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        alignTableContents(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void alignTableContents(int dir) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(dir);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
    }

    private void initializeGlobal() {
        jsonReader = new JsonReader(JSON_PATH);
        jsonWriter = new JsonWriter(JSON_PATH);

        table = new CustomJTable(new TableModel(loadData()));
        rightClickMenu = new JPopupMenu();
    }

    private CashFlowAccount loadData() {
        try {
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
        return new CashFlowAccount();
    }

    private void updateData(CashFlowAccount account) {
        try {
            jsonWriter.write(account);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PATH);
        }
    }

    public void addRow(Item item) {
        ((CustomJTable) table).addRow(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("remove")) {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                ((CustomJTable) table).removeRow(table.getSelectedRow());
            }
        }
    }
}
