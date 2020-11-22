package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for ExpenseItem class
class ExpenseItemTest extends ItemTest {

    @BeforeEach
    public void runBefore() {
        item = new ExpenseItem(TEST_LABEL, TEST_AMOUNT, TEST_DATE, Item.DEFAULT_CATEGORY);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(ExpenseItem.CATEGORIES);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new ExpenseItem(TEST_LABEL, TEST_AMOUNT, TEST_DATE, Item.DEFAULT_CATEGORY));
    }

    @Test
    public void testEmptyConstructor() {
        super.testEmptyConstructor(new ExpenseItem());
    }

    @Test
    public void testGetClassName() {
        assertEquals("ExpenseItem", item.getClassName());
    }

}
