package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for ExpenseItem class
class ExpenseItemTest extends ItemTest {

    @BeforeEach
    public void runBefore() {
        item = new ExpenseItem(LABEL, AMOUNT, DATE);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(ExpenseItem.categories);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new ExpenseItem(LABEL, AMOUNT, DATE));
    }

}
