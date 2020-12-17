package persistence;

import model.Item;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Adapted from JsonSerializationDemo
public class JsonTest {
    protected void checkItem(String label, Double amount, String date, String category, Item item) {
        assertEquals(label, item.getDescription());
        assertEquals(LocalDate.parse(date), item.getDate());
        assertEquals(amount, item.getAmount());
        assertEquals(category, item.getCategory());
    }

    protected void checkItem(String label, Double amount, String date, String category, String type, Item item) {
        assertEquals(type, item.getClassName());
        checkItem(label, amount, date, category, item);
    }
}
