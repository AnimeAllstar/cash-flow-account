package ui;

import model.CashFlowAccount;
import model.Item;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// represents a JDialog used to display pie charts
public class PieChartDialog extends JDialog {

    private PieDataset dataset;
    private JTabbedPane tabbedPane;

    /*
     * EFFECTS: calls super constructor
     *          initializes and adds charts tabbedPane
     *          sets contentPane to tabbedPane
     */
    public PieChartDialog(JFrame frame, Dialog.ModalityType modeless, CashFlowAccount account) {
        super(frame, "Pie Chart", modeless);
        this.setMinimumSize(new Dimension(550, 500));

        initializeGlobal();

        addChartToTabbedPane("Income Items", account.getItemList("IncomeItem"));
        addChartToTabbedPane("Expense Items", account.getItemList("ExpenseItem"));
        setContentPane(tabbedPane);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a Panel to tabbedPane
     */
    private void addChartToTabbedPane(String title, List<Item> items) {
        tabbedPane.addTab(title, createPanel(items, title));
    }

    /*
     * EFFECTS: returns new ChartPanel
     */
    private JPanel createPanel(List<Item> items, String title) {
        return new ChartPanel(createChart(createPieDataSet(computeAmounts(items)), title));
    }

    /*
     * EFFECTS: returns new JFreeChart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    /*
     * MODIFIES: Map<String, Double> map = new HashMap<>()
     * EFFECTS: for each elem in itemList
     *            category = elem.getCategory()
     *            if category is a key in map
     *              - set "mapValue" to map.get(category)
     *            map.put(category, mapValue + elem.getAmount())
     *          return map
     */
    private Map<String, Double> computeAmounts(List<Item> itemList) {
        Map<String, Double> map = new HashMap<>();
        for (Item elem : itemList) {
            String category = elem.getCategory();
            double mapValue = 0;
            if (map.containsKey(category)) {
                mapValue = map.get(category);
            }
            map.put(category, mapValue + elem.getAmount());
        }
        return map;
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes global variables
     */
    private void initializeGlobal() {
        dataset = new DefaultPieDataset();
        tabbedPane = new JTabbedPane();
    }

    /*
     * MODIFIES: DefaultPieDataset dataset = new DefaultPieDataset()
     * EFFECTS: creates a dataset using a Map of categories and their amounts
     *          returns the dataset
     */
    private PieDataset createPieDataSet(Map<String, Double> map) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        map.forEach(dataset::setValue);
        return dataset;
    }
}
