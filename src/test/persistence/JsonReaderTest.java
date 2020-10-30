package persistence;

import model.CashFlowAccount;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for JsonReader class
// Adapted from JsonSerializationDemo
class JsonReaderTest extends persistence.JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected, none thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyItemList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyItemList.json");
        try {
            CashFlowAccount acc = reader.read();
            assertEquals(0, acc.getItemList().size());
        } catch (IOException e) {
            fail("Couldn't read from file. IOException Caught, none expected.");
        }
    }

    @Test
    void testReaderNotEmptyItemList() {
        JsonReader reader = new JsonReader("./data/testReaderTestItemList.json");
        try {
            CashFlowAccount acc = reader.read();
            List<Item> itemList = acc.getItemList();
            assertEquals(2, itemList.size());
            checkItem("tuition fees", (double) 20000, "2020-02-20", "education", "ExpenseItem", itemList.get(0));
            checkItem("google stocks", (double) 5000, "2015-10-20", "investment", "IncomeItem", itemList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file. IOException Caught, none expected.");
        }
    }
}