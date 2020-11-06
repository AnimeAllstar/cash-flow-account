package ui;

import model.ExpenseItem;
import model.IncomeItem;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddItemDialog extends JDialog implements ItemListener {

    private final String[] itemTypes = {"IncomeItem", "ExpenseItem"};

    private JLabel amountLabel;
    private JLabel labelLabel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel dateLabel;

    private JTextField labelField;
    private JTextField dateField;
    private JTextField amountField;

    private JComboBox<Object> categoryComboBox;
    private JComboBox<String> typeComboBox;

    private JPanel labelPane;
    private JPanel fieldPane;

    private JButton addBtn;

    private Item newItem;

    private final ArrayList<JComponent> validateFieldList = new ArrayList<>();

    public AddItemDialog(JFrame frame, ModalityType documentModal) {
        super(frame, "Add Item", documentModal);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(325, 250));

        initializeComponents();
        createLabels();
        addComponentsToPanes();

        this.setLayout(new BorderLayout());
        this.add(fieldPane, BorderLayout.CENTER);
        this.add(labelPane, BorderLayout.WEST);
        this.add(addBtn, BorderLayout.PAGE_END);
    }

    public void close() {
        this.dispose();
    }

    public Item getValue() {
        return newItem;
    }

    private void createLabels() {
        amountLabel = new JLabel("  Amount");
        categoryLabel = new JLabel("  Category");
        typeLabel = new JLabel("  Type");
        dateLabel = new JLabel("  Date (yyyy-MM-dd)  ");
        labelLabel = new JLabel("  Label");
    }

    private void addComponentsToPanes() {
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

    private void initializeComponents() {
        labelField = new JTextField();
        labelField.setName("Label");
        categoryComboBox = new JComboBox<>(IncomeItem.categories.toArray());
        typeComboBox = new JComboBox<>(itemTypes);
        typeComboBox.addItemListener(this);

        amountField = new JTextField();
        amountField.setName("Amount");
        dateField = new JTextField();
        dateField.setName("Date");

        labelPane = new JPanel(new GridLayout(0, 1));
        fieldPane = new JPanel(new GridLayout(0, 1));

        addBtn = new JButton("Add Item");
        addBtn.addActionListener(e -> addBtnListener());

        validateFieldList.add(labelField);
        validateFieldList.add(amountField);
        validateFieldList.add(dateField);
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

    public void addBtnListener() {
        if (validateInputs()) {
            createItem();
        }
    }

    private boolean validateInputs() {
        CustomVerifier verifier = new CustomVerifier();
        for (JComponent field : validateFieldList) {
            if (!verifier.shouldYieldFocus(field)) {
                return false;
            }
        }
        return true;
    }

    public void createItem() {
        if (typeComboBox.getSelectedItem().equals("IncomeItem")) {
            newItem = new IncomeItem(labelField.getText(), Double.parseDouble(amountField.getText()),
                    LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
        } else if (typeComboBox.getSelectedItem().equals("ExpenseItem")) {
            newItem = new ExpenseItem(labelField.getText(), Double.parseDouble(amountField.getText()),
                    LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
        }
        close();
    }
}
