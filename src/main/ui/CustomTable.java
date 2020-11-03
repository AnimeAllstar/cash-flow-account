package ui;

import model.Item;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CustomTable extends AbstractTableModel {

    private final String[] columnNames = {"Label", "Amount", "Date", "Category"};
    List<Item> data;

    public CustomTable(List<Item> data) {
        this.data = data;
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
}
