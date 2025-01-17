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

    private ArrayList<JComponent> validateFieldList;
    private JLabel amountLabel;
    private JLabel descriptionLabel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel dateLabel;
    private JTextField descriptionField;
    private JTextField dateField;
    private JTextField amountField;
    private JComboBox<Object> categoryComboBox;
    private JComboBox<Object> typeComboBox;
    private JPanel labelPane;
    private JPanel fieldPane;
    private JButton addBtn;
    private CustomVerifier verifier;
    private boolean itemAdded;

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
     * EFFECTS: initializes JLabels
     */
    private void createLabels() {
        amountLabel = new JLabel("  Amount");
        categoryLabel = new JLabel("  Category");
        typeLabel = new JLabel("  Type");
        dateLabel = new JLabel("  Date (yyyy-MM-dd)  ");
        descriptionLabel = new JLabel("  Description");
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds labels and fields to labelPane and FieldPane
     */
    private void addComponentsToPanes() {
        labelPane.add(typeLabel);
        labelPane.add(descriptionLabel);
        labelPane.add(amountLabel);
        labelPane.add(dateLabel);
        labelPane.add(categoryLabel);

        fieldPane.add(typeComboBox);
        fieldPane.add(descriptionField);
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
        descriptionField = new JTextField();
        descriptionField.setName("Description");
        categoryComboBox = new JComboBox<>(IncomeItem.CATEGORIES.toArray());
        typeComboBox = new JComboBox<>(Item.ITEMS_TYPES.toArray());
        typeComboBox.addItemListener(this);

        amountField = new JTextField();
        amountField.setName("Amount");
        dateField = new JTextField();
        dateField.setName("Date");

        labelPane = new JPanel(new GridLayout(0, 1));
        fieldPane = new JPanel(new GridLayout(0, 1));

        addBtn = new JButton("Add Item");
        addBtn.addActionListener(e -> addBtnListener());

        verifier = new CustomVerifier();
        validateFieldList = new ArrayList<>();
        validateFieldList.add(descriptionField);
        validateFieldList.add(amountField);
        validateFieldList.add(dateField);

        itemAdded = false;
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
            if (type.equals(IncomeItem.CLASS_NAME)) {
                model = new DefaultComboBoxModel<>(IncomeItem.CATEGORIES.toArray());
            } else if (type.equals(ExpenseItem.CLASS_NAME)) {
                model = new DefaultComboBoxModel<>(ExpenseItem.CATEGORIES.toArray());
            }
            categoryComboBox.setModel(model);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if user inputs are valid
     *            - itemAdded = true
     *            - close this
     */
    public void addBtnListener() {
        if (validateInputs()) {
            itemAdded = true;
            this.dispose();
        }
    }

    /*
     * EFFECTS: return false if any of the elements in validateFieldList have an invalid value
     *          otherwise, return true
     */
    private boolean validateInputs() {
        for (JComponent field : validateFieldList) {
            if (!verifier.shouldYieldFocus(field)) {
                return false;
            }
        }
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if an item was added
     *            - returns new Item using user inputs
     *          otherwise, returns null
     */
    public Item getNewItem() {
        if (itemAdded) {
            if (Objects.equals(typeComboBox.getSelectedItem(), IncomeItem.CLASS_NAME)) {
                return new IncomeItem(descriptionField.getText(), Double.parseDouble(amountField.getText()),
                        LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
            } else if (Objects.equals(typeComboBox.getSelectedItem(), ExpenseItem.CLASS_NAME)) {
                return new ExpenseItem(descriptionField.getText(), Double.parseDouble(amountField.getText()),
                        LocalDate.parse(dateField.getText()), (String) categoryComboBox.getSelectedItem());
            }
        }
        return null;
    }
}
