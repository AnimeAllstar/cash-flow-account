package ui;

import model.ExpenseItem;
import model.IncomeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddItemDialog extends JDialog implements ItemListener {

    private final String[] itemTypes = {"IncomeItem", "ExpenseItem"};
    private JLabel amountLabel;
    private JLabel labelLabel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel dateLabel;
    private JTextField labelField;
    private JComboBox<Object> categoryComboBox;
    private JTextField dateField;
    private JTextField amountField;
    private JComboBox<String> typeComboBox;
    private JPanel labelPane;
    private JPanel fieldPane;

    public AddItemDialog(JFrame frame, ModalityType documentModal) {
        super(frame, "Add Item", documentModal);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 250));

        initializeGLocal();
        createLabels();
        addComponents();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.add(fieldPane, BorderLayout.CENTER);
        this.add(labelPane, BorderLayout.WEST);

    }

    private void createLabels() {
        amountLabel = new JLabel("Amount");
        amountLabel.setLabelFor(amountField);

        categoryLabel = new JLabel("Category");
        categoryLabel.setLabelFor(categoryComboBox);

        typeLabel = new JLabel("Type");
        typeLabel.setLabelFor(typeComboBox);

        dateLabel = new JLabel("Date");
        dateLabel.setLabelFor(dateField);

        labelLabel = new JLabel("Label");
        labelLabel.setLabelFor(labelField);
    }

    private void addComponents() {
        labelPane.add(typeLabel);
        labelPane.add(labelLabel);
        labelPane.add(amountLabel);
        labelPane.add(dateLabel);
        labelPane.add(categoryLabel);

        fieldPane.add(typeComboBox);
        fieldPane.add(labelField);
        fieldPane.add(amountField);
        fieldPane.add(dateField);
        fieldPane.add(categoryComboBox);
    }

    private void initializeGLocal() {
        labelField = new JTextField(15);
        categoryComboBox = new JComboBox<>(IncomeItem.categories.toArray());
        amountField = new JFormattedTextField();
        typeComboBox = new JComboBox<>(itemTypes);
        typeComboBox.addItemListener(this);

        labelPane = new JPanel(new GridLayout(0, 1));
        fieldPane = new JPanel(new GridLayout(0, 1));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateField = new JFormattedTextField(dateFormat);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            DefaultComboBoxModel<Object> model = null;
            String type = (String) e.getItem();
            if (type.equals("IncomeItem")) {
                model = new DefaultComboBoxModel<>(IncomeItem.categories.toArray());
            } else if (type.equals("ExpenseItem")) {
                model = new DefaultComboBoxModel<>(ExpenseItem.categories.toArray());
            }
            categoryComboBox.setModel(model);
        }
    }
}
