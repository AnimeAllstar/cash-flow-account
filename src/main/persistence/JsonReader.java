package persistence;

import model.CashFlowAccount;
import model.ExpenseItem;
import model.IncomeItem;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// Represents a reader that reads CashFlowAccount from JSON data stored in file
// Adapted from JsonSerializationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CashFlowAccount from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CashFlowAccount read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCashFlowAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CashFlowAccount from JSON object and returns it
    private CashFlowAccount parseCashFlowAccount(JSONObject jsonObject) {
        CashFlowAccount acc = new CashFlowAccount();
        addItems(acc, jsonObject);
        return acc;
    }

    // MODIFIES: acc
    // EFFECTS: parses items from JSON object and adds them to CashFlowAccount
    private void addItems(CashFlowAccount acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray(JsonWriter.TAG);
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(acc, nextThingy);
        }
    }

    // MODIFIES: acc
    // EFFECTS: parses Item from JSON object and adds it to CashFlowAccount
    private void addItem(CashFlowAccount acc, JSONObject jsonObject) {
        Item item;
        if (jsonObject.getString("type").equals(IncomeItem.CLASS_NAME)) {
            item = new IncomeItem();
        } else {
            item = new ExpenseItem();
        }
        item.setDescription(jsonObject.getString("description"));
        item.setAmount(jsonObject.getDouble("amount"));
        item.setDate(LocalDate.parse(jsonObject.getString("date")));
        item.setCategory(jsonObject.getString("category"));
        acc.addItem(item);
    }
}
