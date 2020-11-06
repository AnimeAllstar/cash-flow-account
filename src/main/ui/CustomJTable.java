package ui;

import model.Item;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.util.List;

// represents a subclass of JTable
public class CustomJTable extends JTable {

    public CustomJTable(TableModel tableModel) {
        super(tableModel);
    }

    /*
     * EFFECTS: returns an appropriate CellEditor for the selected cell
     *          for columns 0 - 2, returns CustomCellEditor
     *          for column 3, returns DefaultCellEditor
     *          otherwise, returns super.getCellEditor(row, column)
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: remove selected row from this.getModel()
     */
    public void removeRow(int selectedRow) {
        ((TableModel) this.getModel()).removeRow(selectedRow);
    }

    /*
     * MODIFIES: this
     * EFFECTS: add row using item to this.getModel()
     */
    public void addRow(Item item) {
        ((TableModel) this.getModel()).addRow(item);
    }
}
