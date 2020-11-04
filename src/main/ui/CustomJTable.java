package ui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.util.List;

public class CustomJTable extends JTable {

    public CustomJTable(TableModel tableModel) {
        super(tableModel);
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        List<String> itemList = ((TableModel) this.getModel()).getCategoryList(row);
        if (column == 3) {
            return new DefaultCellEditor(new JComboBox<>(itemList.toArray()));
        } else {
            return super.getCellEditor(row, column);
        }
    }

    public void removeRow(int selectedRow) {
        ((TableModel) this.getModel()).removeRow(selectedRow);
    }
}
