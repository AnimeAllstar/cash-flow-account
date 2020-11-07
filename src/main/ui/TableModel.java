package ui;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

// represents a subclass of AbstractTableModel to be used as a model for a JTable
public class TableModel extends AbstractTableModel {

    protected final String[] columnNames = {"Label", "Amount", "Date", "Category", "Type"};
    CashFlowAccount cashFlowAccount;

    /*
     * EFFECTS: initializes and sorts cashFlowAccount
     *          notifies listeners that table data may have changed
     */
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

    /*
     * EFFECTS: returns value of cell at row,col
     */
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
            default:
                return null;
        }
    }

    /*
     * EFFECTS: returns a list of categories based on the type of Item in row
     */
    public ArrayList<String> getCategoryList(int row) {
        if (getValueAt(row, 4).equals("IncomeItem")) {
            return IncomeItem.categories;
        }
        return ExpenseItem.categories;
    }

    public boolean isCellEditable(int row, int col) {
        return col < 4;
    }

    /*
     * MODIFIES: this
     * EFFECTS: overwrites cell data at row,col with value
     *          sorts cashFlowAccount
     *          notifies listeners that table data may have changed
     */
    public void setValueAt(Object value, int row, int col) {
        Item item = cashFlowAccount.getItem(row);
        switch (col) {
            case 0:
                item.setLabel((String) value);
                break;
            case 1:
                item.setAmount(Double.parseDouble((String) value));
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

    /*
     * MODIFIES: this
     * EFFECTS: removes element at selectedRow
     *          notifies listeners that selectedRow has been deleted
     */
    public void removeRow(int selectedRow) {
        cashFlowAccount.removeItem(selectedRow);
        fireTableRowsDeleted(selectedRow, selectedRow);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds item to cashFlowAccount
     *          sorts cashFlowAccount
     *          notifies listeners that table data may have changed
     */
    public void addRow(Item item) {
        cashFlowAccount.addItem(item);
        cashFlowAccount.sortItems();
        fireTableDataChanged();
    }
}
