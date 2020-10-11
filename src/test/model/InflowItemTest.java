package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for InflowItem class
class InflowItemTest {

    private static final LocalDate DATE = LocalDate.of(2020, Month.OCTOBER, 1);
    private static final double AMOUNT = 150.65;
    private ListItem listItem;

    @BeforeEach
    public void runBefore() {
        listItem = new InflowItem(AMOUNT, DATE);
    }

    @Test
    public void testInit() {
        assertEquals(AMOUNT, listItem.getAmount());
        assertEquals(DATE, listItem.getDate());
        assertEquals("uncategorized", listItem.getCategory());
    }


    @Test
    public void testCompareTo() {
        ListItem tempItem = new InflowItem(AMOUNT, DATE);
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

    @Test
    public void testSetCategories() {
        assertEquals("uncategorized", listItem.getCategory());
        assertFalse(listItem.setCategory("Invalid", InflowItem.categories));
        assertTrue(listItem.setCategory("shopping", InflowItem.categories));
    }

}