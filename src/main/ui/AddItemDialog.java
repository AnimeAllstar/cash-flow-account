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
import java.util.Objects;

// represents a JDialog used to add new items
public class AddItemDialog extends JDialog implements ItemListener {

    private final String[] itemTypes = {"IncomeItem", "ExpenseItem"};
    private ArrayList<JComponent> validateFieldList;
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

    /*
     * EFFECTS: calls super constructor
     *          initializes and adds GUI components to this
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: closes this
     */
    public void close() {
        this.dispose();
    }

    /*
     * EFFECTS: returns newItem
     */
    public Item getValue() {
        return newItem;
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes JLabels
     */
    private void createLabels() {
        amountLabel = new JLabel("  Amount");
        categoryLabel = new JLabel("  Category");
        typeLabel = new JLabel("  Type");
        dateLabel = new JLabel("  Date (yyyy-MM-dd)  ");
        labelLabel = new JLabel("  Label");
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds labels and fields to labelPane and FieldPane
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: initializes JComponents
     *          adds fields that need to be validated to validateFieldList
     */
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

        validateFieldList = new ArrayList<>();
        validateFieldList.add(labelField);
        validateFieldList.add(amountField);
        validateFieldList.add(dateField);
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes ComboBoxModel of categoryComboBox if there is a change in typeComboBox
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: if user inputs are valid
     *            - create item user inputs
     *            - close this
     */
    public void addBtnListener() {
        if (validateInputs()) {
            createItem();
            close();
        }
    }

    /*
     * EFFECTS: return false if any of the elements in validateFieldList have an invalid value
     *          otherwise, return true
     */
    private boolean validateInputs() {
        CustomVerifier verifier = new CustomVerifier();
        for (JComponent field : validateFieldList) {
            if (!verifier.shouldYieldFocus(field)) {
                return false;
            }
        }
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes newItem using user inputs
     */
    public void createItem() {
        if (Objects.equals(typeComboBox.getSelectedItem(), "IncomeItem")) {
            newItem = new IncomeItem(labelField.getText(), Double.parseDouble(amountField.getText()),
                    LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
        } else if (Objects.equals(typeComboBox.getSelectedItem(), "ExpenseItem")) {
            newItem = new ExpenseItem(labelField.getText(), Double.parseDouble(amountField.getText()),
                    LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
        }
    }
}
