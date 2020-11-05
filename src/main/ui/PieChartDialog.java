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

public class PieChartDialog extends JDialog {
    private List<Item> incomeItems;
    private List<Item> expenseItems;

    private PieDataset dataset;
    private JTabbedPane tabbedPane;

    public PieChartDialog(JFrame frame, Dialog.ModalityType modeless, CashFlowAccount account) {
        super(frame, "Pie Chart", modeless);
        this.setMinimumSize(new Dimension(550, 500));

        initializeGlobal(account);
        addChartsToTabbedPane();
        setContentPane(tabbedPane);
    }

    private void addChartsToTabbedPane() {
        tabbedPane.addTab("Income Items", createPanel(incomeItems, "Income Items"));
        tabbedPane.addTab("Expense Items", createPanel(expenseItems, "Expense Items"));
    }

    private JPanel createPanel(List<Item> items, String title) {
        return new ChartPanel(createChart(createPieDataSet(computeAmounts(items)), title));
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

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

    private void initializeGlobal(CashFlowAccount account) {
        dataset = new DefaultPieDataset();
        tabbedPane = new JTabbedPane();
        incomeItems = account.getItemList("IncomeItem");
        expenseItems = account.getItemList("ExpenseItem");
    }

    private PieDataset createPieDataSet(List<Category> dateList) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Category elem : dateList) {
            dataset.setValue(elem.name, elem.amount);
        }
        return dataset;
    }

    private class Category {
        private final String name;
        private double amount;

        public Category(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        public boolean equals(Category category) {
            return this.name.equals(category.name);
        }
    }
}
