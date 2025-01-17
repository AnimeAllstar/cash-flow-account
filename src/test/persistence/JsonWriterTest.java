package persistence;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for JsonWriter class
// Adapted from JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CashFlowAccount acc = new CashFlowAccount();
            JsonWriter writer = new JsonWriter("./data\0illegal\fileName.json");
            writer.write(acc);
            fail("IOException expected, none thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyItemList() {
        try {
            CashFlowAccount acc = new CashFlowAccount();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyItemList.json");
            writer.write(acc);

            JsonReader reader = new JsonReader("./data/testWriterEmptyItemList.json");
            acc = reader.read();
            assertEquals(0, acc.getItemList().size());
        } catch (IOException e) {
            fail("Couldn't write to file. IOException Caught, none expected.");
        }
    }

    @Test
    void testWriterNotEmptyItemList() {
        try {
            CashFlowAccount acc = new CashFlowAccount();
            acc.addItem(new ExpenseItem("tuition fees", 20000, LocalDate.parse("2020-02-20"), "education"));
            acc.addItem(new IncomeItem("google stocks", 5000, LocalDate.parse("2015-10-20"), "investment"));
            JsonWriter writer = new JsonWriter("./data/testWriterTestItemList.json");
            writer.write(acc);

            JsonReader reader = new JsonReader("./data/testWriterTestItemList.json");
            acc = reader.read();
            List<Item> itemList = acc.getItemList();
            assertEquals(2, itemList.size());
            checkItem("tuition fees", (double) 20000, "2020-02-20", "education", ExpenseItem.CLASS_NAME, itemList.get(0));
            checkItem("google stocks", (double) 5000, "2015-10-20", "investment", IncomeItem.CLASS_NAME, itemList.get(1));
        } catch (IOException e) {
            fail("Couldn't write to file. IOException Caught, none expected.");
        }
    }
}