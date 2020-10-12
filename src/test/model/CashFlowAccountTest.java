package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for CashFlowAccount class
class CashFlowAccountTest {

    private CashFlowAccount cashFlowAccount;

    @BeforeEach
    public void runBefore() {
        cashFlowAccount = new CashFlowAccount();
    }

    @Test
    public void testGetItemList() {

        assertEquals(0, cashFlowAccount.getItemList().size());
        assertEquals(0, cashFlowAccount.getItemList("IncomeItem").size());
        assertEquals(0, cashFlowAccount.getItemList("ExpenseItem").size());

        cashFlowAccount.addItem(new IncomeItem());
        cashFlowAccount.addItem(new ExpenseItem());
        cashFlowAccount.addItem(new ExpenseItem());

        assertEquals(3, cashFlowAccount.getItemList().size());
        assertEquals(1, cashFlowAccount.getItemList("IncomeItem").size());
        assertEquals(2, cashFlowAccount.getItemList("ExpenseItem").size());

    }

    @Test
    public void testRemoveItem() {
        assertFalse(cashFlowAccount.removeItem(new IncomeItem()));
        assertEquals(null, cashFlowAccount.getItem(0));
        cashFlowAccount.addItem(new IncomeItem());
        cashFlowAccount.addItem(new ExpenseItem());
        cashFlowAccount.addItem(new ExpenseItem());
        assertEquals(3, cashFlowAccount.getItemList().size());
        assertTrue(cashFlowAccount.removeItem(cashFlowAccount.getItem(0)));
        assertEquals(2, cashFlowAccount.getItemList().size());
    }

}