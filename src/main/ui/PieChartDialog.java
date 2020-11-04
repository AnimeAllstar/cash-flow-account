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
    PieDataset dataset;
    private List<Item> incomeItems;
    private List<Item> expenseItems;
    private JTabbedPane tabbedPane;

    public PieChartDialog(JFrame frame, Dialog.ModalityType modeless, CashFlowAccount account) {
        super(frame, "Pie Chart", modeless);
        initializeGlobal(account);
        this.setMinimumSize(new Dimension(550, 500));
        addChartsToPane();
        setContentPane(tabbedPane);
    }

    private void addChartsToPane() {
        tabbedPane.addTab("Income Items", createPanel(incomeItems, "Income Items"));
        tabbedPane.addTab("Expense Items", createPanel(expenseItems, "Expense Items"));
    }

    private JPanel createPanel(List<Item> items, String title) {
        JFreeChart chart = createChart(getPieDataSet(computePercentages(items)), title);
        return new ChartPanel(chart);
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    private List<Category> computePercentages(List<Item> itemList) {
        List<Category> categoryList = new ArrayList<>();
        for (Item elem : itemList) {
            Category category = new Category(elem.getCategory(), elem.getAmount());
            Iterator<Category> it = categoryList.iterator();
            boolean contains = false;
            while (it.hasNext()) {
                Category c = it.next();
                if (c.equals(category)) {
                    c.amount += category.amount;
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                categoryList.add(category);
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

    private PieDataset getPieDataSet(List<Category> dateList) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Category elem : dateList) {
            dataset.setValue(elem.name, elem.amount);
        }
        return dataset;
    }

    private class Category {
        String name;
        double amount;

        public Category(String category, double amnt) {
            name = category;
            amount = amnt;
        }

        public boolean equals(Category category) {
            return this.name.equals(category.name);
        }
    }
}
