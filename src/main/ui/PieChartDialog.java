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
import java.util.ArrayList;
import java.util.Iterator;
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
     * EFFECTS: calculates the total amount spent on each Item category in itemList
     *          returns total as a list of Category objects
     */
    private List<Category> computeAmounts(List<Item> itemList) {
        List<Category> categoryList = new ArrayList<>();
        for (Item elem : itemList) {
            Category currentCategory = new Category(elem.getCategory(), elem.getAmount());
            Iterator<Category> it = categoryList.iterator();
            boolean contains = false;
            while (it.hasNext()) {
                Category existingCategory = it.next();
                if (existingCategory.equals(currentCategory)) {
                    existingCategory.amount += currentCategory.amount;
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                categoryList.add(currentCategory);
            }
        }
        return categoryList;
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
     * EFFECTS: creates a dataset using a list of categories
     *          returns the dataset
     */
    private PieDataset createPieDataSet(List<Category> dataList) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Category elem : dataList) {
            dataset.setValue(elem.name, elem.amount);
        }
        return dataset;
    }

    // represents an inner class used to store category names and the total amount spent on them
    private class Category {
        private final String name;
        private double amount;

        public Category(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        /*
         * EFFECTS: returns whether this and category have the same name
         */
        public boolean equals(Category category) {
            return this.name.equals(category.name);
        }
    }
}
