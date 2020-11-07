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

// represents a JDialog used to display pie charts
public class PieChartDialog extends JDialog {
    private List<Item> incomeItems;
    private List<Item> expenseItems;

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

        initializeGlobal(account);
        addChartsToTabbedPane();
        setContentPane(tabbedPane);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds panels to tabbedPane
     */
    private void addChartsToTabbedPane() {
        tabbedPane.addTab("Income Items", createPanel(incomeItems, "Income Items"));
        tabbedPane.addTab("Expense Items", createPanel(expenseItems, "Expense Items"));
    }

    /*
     * EFFECTS: returns new CharPanel
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
     * EFFECTS: for each elem in itemList
     *            category = elem.getCategory()
     *            if category is a key in map
     *              - set "mapValue" to map.get(category)
     *            map.put(category, mapValue + elem.getAmount())
     *          return map
     */
    private HashMap<String, Double> computeAmounts(List<Item> itemList) {
        HashMap<String, Double> map = new HashMap<>();
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
     * EFFECTS: initializes global variables
     */
    private void initializeGlobal(CashFlowAccount account) {
        dataset = new DefaultPieDataset();
        tabbedPane = new JTabbedPane();
        incomeItems = account.getItemList("IncomeItem");
        expenseItems = account.getItemList("ExpenseItem");
    }

    /*
     * EFFECTS: creates a dataset using a HashMap of categories and their amounts
     *          returns the dataset
     */
    private PieDataset createPieDataSet(HashMap<String, Double> map) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        map.forEach(dataset::setValue);
        return dataset;
    }
}
