package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Item class
public abstract class ItemTest {
    protected static final LocalDate TEST_DATE = LocalDate.of(2020, Month.OCTOBER, 1);
    protected static final String TEST_DATE_STR = TEST_DATE.toString();
    protected static final double TEST_AMOUNT = 150.65;
    protected static final String TEST_DESCRIPTION = "testItem";
    protected Item item;

    @Test
    public void testInit() {
        assertEquals(TEST_AMOUNT, item.getAmount());
        assertEquals(TEST_DATE, item.getDate());
        assertEquals(Item.DEFAULT_CATEGORY, item.getCategory());
        assertEquals(TEST_DESCRIPTION, item.getDescription());
    }

    @Test
    public void testToString() {
        String expected = TEST_DESCRIPTION + "             $ " + TEST_AMOUNT + "          " + TEST_DATE_STR + "   "
                + Item.DEFAULT_CATEGORY + "  ";
        assertEquals(expected, item.toString());
    }

    @Test
    public void testSetDescription() {
        item.setDescription(TEST_DESCRIPTION + 1);
        assertEquals(TEST_DESCRIPTION + 1, item.getDescription());
    }

    public void testCompareTo(Item newItem) {
        assertEquals(0, item.compareTo(newItem));

        newItem.setAmount(TEST_AMOUNT + 100);
        assertEquals(TEST_AMOUNT + 100, newItem.getAmount());
        assertEquals(100, item.compareTo(newItem));

        newItem.setAmount(TEST_AMOUNT - 100);
        assertEquals(TEST_AMOUNT - 100, newItem.getAmount());
        assertEquals(-100, item.compareTo(newItem));

        newItem.setDate(LocalDate.now());
        assertEquals(LocalDate.now(), newItem.getDate());
        assertEquals(-item.getDate().compareTo(newItem.getDate()), item.compareTo(newItem));
        assertEquals(-item.getDateString().compareTo(newItem.getDateString()), item.compareTo(newItem));
    }

    public void testEmptyConstructor(Item newItem) {
        assertEquals(Item.DEFAULT_AMOUNT, newItem.getAmount());
        assertEquals(Item.DEFAULT_DATE, newItem.getDate());
        assertEquals(Item.DEFAULT_CATEGORY, newItem.getCategory());
        assertEquals(Item.DEFAULT_DESCRIPTION, newItem.getDescription());
    }

    public void testSetCategories(ArrayList<String> categories) {
        assertEquals(Item.DEFAULT_CATEGORY, item.getCategory());
        assertFalse(item.setCategory(categories.size() + 1, categories));
        assertFalse(item.setCategory(-1, categories));
        assertTrue(item.setCategory(0, categories));
    }
}
