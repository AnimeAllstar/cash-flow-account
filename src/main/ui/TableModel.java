package ui;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private final String[] columnNames = {"Label", "Amount", "Date", "Category", "Type"};
    CashFlowAccount cashFlowAccount;

    public TableModel(CashFlowAccount account) {
        cashFlowAccount = account;
        cashFlowAccount.sortItems();
        fireTableDataChanged();
    }

    public CashFlowAccount getCashFlowAccount() {
        return cashFlowAccount;
    }

    @Override
    public int getRowCount() {
        return cashFlowAccount.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    public Object getValueAt(int row, int col) {
        Item item = cashFlowAccount.getItem(row);
        switch (col) {
            case 0:
                return item.getLabel();
            case 1:
                return item.getAmount();
            case 2:
                return item.getDateString();
            case 3:
                return item.getCategory();
            case 4:
                return item.getClassName();
        }
        return null;
    }

    public ArrayList<String> getCategoryList(int row) {
        if (IncomeItem.categories.contains(getValueAt(row, 3))) {
            return IncomeItem.categories;
        } else if (ExpenseItem.categories.contains(getValueAt(row, 3))) {
            return ExpenseItem.categories;
        }
        return null;
    }

    public List<Item> getData() {
        return cashFlowAccount.getItemList();
    }

    public boolean isCellEditable(int row, int col) {
        return col < 4;
    }

    public void setValueAt(Object value, int row, int col) {
        Item item = cashFlowAccount.getItem(row);
        switch (col) {
            case 0:
                item.setLabel((String) value);
                break;
            case 1:
                item.setAmount((Double) value);
                break;
            case 2:
                item.setDate(LocalDate.parse((CharSequence) value));
                break;
            case 3:
                item.setCategory((String) value);
                break;
        }
        cashFlowAccount.sortItems();
        fireTableDataChanged();
    }

    public void removeRow(int selectedRow) {
        cashFlowAccount.removeItem(selectedRow);
        fireTableRowsDeleted(selectedRow, selectedRow);
    }

    public void addRow(Item item) {
        cashFlowAccount.addItem(item);
        cashFlowAccount.sortItems();
        fireTableDataChanged();
    }
}
