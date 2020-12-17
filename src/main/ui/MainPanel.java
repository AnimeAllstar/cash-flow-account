package ui;

import model.CashFlowAccount;
import model.Item;
import model.TableModel;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents a subclass of JPanel used to house an object the CustomTable
public class MainPanel extends JPanel implements ActionListener {

    private static final String JSON_PATH = "./data/itemList.json";
    protected JTable table;
    protected JPopupMenu rightClickMenu;
    private JTextField searchBar;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private TableRowSorter<TableModel> sorter;

    /*
     * EFFECTS: sets layout and preferred dimensions for this
     *          calls methods to initialize, configure and add components to this
     */
    public MainPanel() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(950, 750));
        initializeGlobal();
        configureTable();
        configureSearchBar();
        configureRightClickMenu();
    }

    /*
     * MODIFIES: this
     * EFFECTS: configures the SearchBar appearance
     *          adds document listener for changes in searchBar value
     */
    private void configureSearchBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        searchBar.setPreferredSize(new Dimension(searchBar.getWidth(), 30));

        searchBar.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        updateSearchCriteria();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        updateSearchCriteria();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        updateSearchCriteria();
                    }
                });

        panel.add(searchBar, BorderLayout.CENTER);
        panel.add(new JLabel(" Filter Description:  "), BorderLayout.WEST);
        this.add(panel, BorderLayout.SOUTH);
    }

    /*
     * MODIFIES: this
     * EFFECTS: filters JTable for value entered in the searchBar
     */
    private void updateSearchCriteria() {
        RowFilter<TableModel, Object> rf;
        try {
            rf = RowFilter.regexFilter(searchBar.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds rightClickMenu with a "Remove" menu item to table
     */
    private void configureRightClickMenu() {
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.setActionCommand("remove");
        removeItem.addActionListener(this);
        rightClickMenu.add(removeItem);
        table.setComponentPopupMenu(rightClickMenu);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets table model to existing data in the JSON file
     *          center aligns table content
     *          removes search filter
     */
    public void revertChanges() {
        TableModel tableModel = new TableModel(loadData());
        table.setModel(tableModel);
        sorter.setModel(tableModel);
        alignTableContents(SwingConstants.CENTER);
        searchBar.setText("");
    }

    /*
     * MODIFIES: this
     * EFFECTS: overwrites the JSON file with data from the table model
     */
    public void saveChanges() {
        updateData(((TableModel) table.getModel()).getCashFlowAccount());
    }

    /*
     * MODIFIES: this
     * EFFECTS: configures the table appearance
     *          adds table to a scrollPane
     *          adds scrollPane to this
     */
    private void configureTable() {
        table.setFillsViewportHeight(true);
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(sorter);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        alignTableContents(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: aligns table data using alignment
     */
    private void alignTableContents(int alignment) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(alignment);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes global variables
     */
    private void initializeGlobal() {
        jsonReader = new JsonReader(JSON_PATH);
        jsonWriter = new JsonWriter(JSON_PATH);

        TableModel tableModel = new TableModel(loadData());
        table = new CustomJTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        rightClickMenu = new JPopupMenu();
        searchBar = new JTextField();
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads the CashFlowAccount from the file at JSON_PATH
     */
    private CashFlowAccount loadData() {
        try {
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
        return new CashFlowAccount();
    }

    /*
     * EFFECTS: saves the account to the file at JSON_PATH
     */
    private void updateData(CashFlowAccount account) {
        try {
            jsonWriter.write(account);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PATH);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds item to table
     */
    public void addRow(Item item) {
        ((CustomJTable) table).addRow(item);
    }

    /*
     * This method is the ActionListener for the "Remove" menu item
     * MODIFIES: this
     * EFFECTS: if a row is selected, delete selectedRow from table
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("remove")) {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                ((CustomJTable) table).removeRow(rowIndex);
            }
        }
    }
}
