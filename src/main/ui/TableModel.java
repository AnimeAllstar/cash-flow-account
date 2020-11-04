package ui;

import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private final String[] columnNames = {"Label", "Amount", "Date", "Category", "Type"};
    List<Item> data;

    public TableModel(List<Item> data) {
        this.data = data;
        Collections.sort(data);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
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
        switch (col) {
            case 0:
                return data.get(row).getLabel();
            case 1:
                return data.get(row).getAmount();
            case 2:
                return data.get(row).getDateString();
            case 3:
                return data.get(row).getCategory();
            case 4:
                return data.get(row).getClassName();
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
        return data;
    }

    public boolean isCellEditable(int row, int col) {
        return col < 4;
    }

    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 0:
                data.get(row).setLabel((String) value);
                break;
            case 1:
                data.get(row).setAmount((Double) value);
                break;
            case 2:
                data.get(row).setDate(LocalDate.parse((CharSequence) value));
                break;
            case 3:
                data.get(row).setCategory((String) value);
                break;
        }
        Collections.sort(data);
        fireTableDataChanged();
    }

    public void removeRow(int selectedRow) {
        data.remove(selectedRow);
        fireTableRowsDeleted(selectedRow, selectedRow);
    }

    public void addRow(Item item) {
        data.add(item);
        Collections.sort(data);
        fireTableDataChanged();
    }
}
