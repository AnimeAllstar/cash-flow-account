package persistance;

import model.CashFlowAccount;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of CashFlowAccount to file
public class JsonWriter {
    private static final int TAB = 4;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void write(CashFlowAccount acc) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new File(destination))) {
            JSONObject json = acc.toJson();
            pw.print(json.toString(TAB));
        }
    }
}
