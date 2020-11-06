package ui;

import model.Item;

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
        JTextField textField = new JTextField();

        switch (column) {
            case 0:
                textField.setName("Label");
                return new CustomCellEditor(textField);
            case 1:
                textField.setName("Amount");
                return new CustomCellEditor(textField);
            case 2:
                textField.setName("Date");
                return new CustomCellEditor(textField);
            case 3:
                return new DefaultCellEditor(new JComboBox<>(itemList.toArray()));
            default:
                return super.getCellEditor(row, column);
        }
    }

    public void removeRow(int selectedRow) {
        ((TableModel) this.getModel()).removeRow(selectedRow);
    }

    public void addRow(Item item) {
        ((TableModel) this.getModel()).addRow(item);
    }
}
