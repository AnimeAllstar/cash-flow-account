package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Item class
public abstract class ItemTest {
    protected static final LocalDate DATE = LocalDate.of(2020, Month.OCTOBER, 1);
    protected static final double AMOUNT = 150.65;
    protected static final String LABEL = "testItem";
    protected Item item;

    @Test
    public void testInit() {
        assertEquals(AMOUNT, item.getAmount());
        assertEquals(DATE, item.getDate());
        assertEquals("uncategorized", item.getCategory());
    }

    public void testCompareTo(Item tempItem) {
        assertEquals(0, item.compareTo(tempItem));

        tempItem.setAmount(AMOUNT + 100);
        assertEquals(AMOUNT + 100, tempItem.getAmount());
        assertEquals(-1, item.compareTo(tempItem));

        tempItem.setAmount(AMOUNT - 100);
        assertEquals(AMOUNT - 100, tempItem.getAmount());
        assertEquals(1, item.compareTo(tempItem));

        tempItem.setDate(LocalDate.now());
        assertEquals(LocalDate.now(), tempItem.getDate());
        assertEquals(item.getDate().compareTo(tempItem.getDate()), item.compareTo(tempItem));
    }

    public void testSetCategories(ArrayList<String> categories) {
        assertEquals("uncategorized", item.getCategory());
        assertFalse(item.setCategory(-1, categories));
        assertTrue(item.setCategory(0, categories));
    }
}
