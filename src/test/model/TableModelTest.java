package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for TableModel class
public class TableModelTest {

    private CashFlowAccount account;
    private TableModel model;

    @BeforeEach
    void runBefore() {
        account = new CashFlowAccount();
        model = new TableModel(account);
    }

    @Test
    void testInitialization() {
        assertEquals(account, model.getCashFlowAccount());
    }

    @Test
    void testIsEditable() {
        assertFalse(model.isCellEditable(0, model.getColumnCount()));
        assertFalse(model.isCellEditable(0, model.getColumnCount() - 1));
        assertTrue(model.isCellEditable(0, model.getColumnCount() - 2));
    }

    @Test
    void testSetValueAt() {
        ExpenseItem testExpense = new ExpenseItem("name", 100, LocalDate.of(2000, Month.JANUARY, 1), "transport");
        model.addRow(new ExpenseItem());
        model.setValueAt(testExpense.getLabel(), 0, 0);
        model.setValueAt(String.valueOf(testExpense.getAmount()), 0, 1);
        model.setValueAt(String.valueOf(testExpense.getDate()), 0, 2);
        model.setValueAt(testExpense.getCategory(), 0, 3);
        model.setValueAt("Invalid col", 0, 4);
        testGetValueAt(testExpense);
        IncomeItem testIncome = new IncomeItem();
        model.addRow(testIncome);
        testGetValueAt(testIncome);
    }

    void testGetValueAt(Item testItem) {
        assertEquals(testItem.getLabel(), model.getValueAt(0, 0));
        assertEquals(testItem.getAmount(), model.getValueAt(0, 1));
        assertEquals(testItem.getDate(), LocalDate.parse((CharSequence) model.getValueAt(0, 2)));
        assertEquals(testItem.getCategory(), model.getValueAt(0, 3));
        assertEquals(testItem.getClassName(), model.getValueAt(0, 4));
        assertNull(model.getValueAt(0, model.getColumnCount()));
    }

    @Test
    void testRemoveRow() {
        assertEquals(0, model.getRowCount());
        model.addRow(new IncomeItem());
        model.addRow(new ExpenseItem());
        model.addRow(new IncomeItem());
        assertEquals(3, model.getRowCount());
        model.removeRow(1);
        assertEquals(2, model.getRowCount());
        assertEquals(model.getValueAt(0, 4), "IncomeItem");
        assertEquals(model.getValueAt(1, 4), "IncomeItem");
    }

    @Test
    void testGetCategory() {
        model.addRow(new IncomeItem());
        assertEquals(model.getCategoryList(0), IncomeItem.categories);
        model.addRow(new ExpenseItem());
        assertEquals(model.getCategoryList(1), ExpenseItem.categories);
        model.addRow(new IncomeItem());
    }

    @Test
    void testGetColumnNameAndClass() {
        model.addRow(new IncomeItem());
        for (int i = 0; i < model.getColumnCount(); i++) {
            assertEquals(model.columnNames[i], model.getColumnName(i));
            assertEquals(model.getColumnClass(i), model.getValueAt(0, i).getClass());
        }
    }
}
