package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for ListItem class
public abstract class ListItemTest {
    protected static final LocalDate DATE = LocalDate.of(2020, Month.OCTOBER, 1);
    protected static final double AMOUNT = 150.65;
    protected ListItem listItem;

    @Test
    public void testInit() {
        assertEquals(AMOUNT, listItem.getAmount());
        assertEquals(DATE, listItem.getDate());
        assertEquals("uncategorized", listItem.getCategory());
    }

    public void testCompareTo(ListItem tempItem) {
        assertEquals(0, listItem.compareTo(tempItem));

        tempItem.setAmount(AMOUNT + 100);
        assertEquals(AMOUNT + 100, tempItem.getAmount());
        assertEquals(-1, listItem.compareTo(tempItem));

        tempItem.setAmount(AMOUNT - 100);
        assertEquals(AMOUNT - 100, tempItem.getAmount());
        assertEquals(1, listItem.compareTo(tempItem));

        tempItem.setDate(LocalDate.now());
        assertEquals(LocalDate.now(), tempItem.getDate());
        assertEquals(listItem.getDate().compareTo(tempItem.getDate()), listItem.compareTo(tempItem));
    }

    public void testSetCategories(ArrayList<String> categories) {
        assertEquals("uncategorized", listItem.getCategory());
        assertFalse(listItem.setCategory("Invalid", categories));
        assertTrue(listItem.setCategory(categories.get(categories.size() - 1), categories));
    }
}
