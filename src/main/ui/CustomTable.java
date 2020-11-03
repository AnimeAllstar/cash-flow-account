package ui;

import model.Item;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.List;

public class CustomTable extends AbstractTableModel {

    private final String[] columnNames = {"Label", "Amount", "Date", "Category"};
    List<Item> data;

    public CustomTable(List<Item> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
                return data.get(row).getDate();
            case 3:
                return data.get(row).getCategory();
        }
        return null;
    }

    public List<Item> getData() {
        return data;
    }

    public boolean isCellEditable(int row, int col) {
        return true;
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
                data.get(row).setDate((LocalDate) value);
                break;
            case 3:
                data.get(row).setCategory((String) value);
                break;
        }
        fireTableCellUpdated(row, col);
    }

//    public void updateRow(Item item, int row) {
//        data.get(row).setCategory(item.getCategory());
//        data.get(row).setLabel(item.getLabel());
//        data.get(row).setAmount(item.getAmount());
//        data.get(row).setDate(item.getDate());
//    }
}
