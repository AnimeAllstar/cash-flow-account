package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for IncomeItem class
class IncomeItemTest extends ItemTest {

    @BeforeEach
    public void runBefore() {
        item = new IncomeItem(TEST_LABEL, TEST_AMOUNT, TEST_DATE, Item.DEFAULT_CATEGORY);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(IncomeItem.CATEGORIES);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new IncomeItem(TEST_LABEL, TEST_AMOUNT, TEST_DATE, Item.DEFAULT_CATEGORY));
    }

    @Test
    public void testEmptyConstructor() {
        super.testEmptyConstructor(new IncomeItem());
    }

    @Test
    public void testGetClassName() {
        assertEquals("IncomeItem", item.getClassName());
    }

}