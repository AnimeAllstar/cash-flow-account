package ui;

import model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a Swing user interface
// contains a frame with mainPanel as the contentPane
// and a menu bar to access other UI elements
public class SwingUI implements ActionListener {

    private JFrame frame;
    private MainPanel mainPanel;
    private AddItemDialog addItemDialog;
    private PieChartDialog pieChartDialog;

    public SwingUI() {
        initializeGlobal();
        configureFrame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: configures frame
     *          adds components to frame
     */
    private void configureFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setJMenuBar(this.addMenuBar());
        frame.pack();
        frame.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes global variables
     */
    public void initializeGlobal() {
        frame = new JFrame("Cash Flow Account");
        mainPanel = new MainPanel();
    }

    /*
     * MODIFIES: JMenuBar menuBar = new JMenuBar()
     * EFFECTS: returns a menuBar with fileMenu, toolMenu and analyseMenu
     */
    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        menuBar.add(toolMenu());
        menuBar.add(analyseMenu());
        return menuBar;
    }

    /*
     * MODIFIES: JMenu menu = new JMenu("File")
     * EFFECTS: returns a menu with save and revert menuItems
     */
    private JMenu fileMenu() {
        JMenu menu = new JMenu("File");

        JMenuItem menuItem = new JMenuItem("Save Changes");
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Revert Changes");
        menuItem.setActionCommand("revert");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menu;
    }

    /*
     * MODIFIES: JMenu menu = new JMenu("Tools")
     * EFFECTS: returns a menu with an add menuItem
     */
    private JMenu toolMenu() {
        JMenu menu = new JMenu("Tools");

        JMenuItem menuItem = new JMenuItem("Add Item");
        menuItem.setActionCommand("add");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menu;
    }

    /*
     * MODIFIES: JMenu menu = new JMenu("Analyse")
     * EFFECTS: returns a menu with an pieChart menuItem
     */
    private JMenu analyseMenu() {
        JMenu menu = new JMenu("Analyse");

        JMenuItem menuItem = new JMenuItem("Pie Chart");
        menuItem.setActionCommand("pieChart");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menu;
    }

    /*
     * MODIFIES: this
     * EFFECTS: calls appropriate methods based on the action command
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "revert":
                mainPanel.revertChanges();
                break;
            case "save":
                mainPanel.saveChanges();
                break;
            case "add":
                createAddItemDialog();
                getItemFromDialog();
                break;
            case "pieChart":
                createPieChartDialog();
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new PieChartDialog
     */
    public void createPieChartDialog() {
        pieChartDialog = new PieChartDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL,
                ((TableModel) mainPanel.table.getModel()).getCashFlowAccount());
        pieChartDialog.setLocationRelativeTo(frame);
        pieChartDialog.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new AddItemDialog
     */
    public void createAddItemDialog() {
        addItemDialog = new AddItemDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL);
        addItemDialog.setLocationRelativeTo(frame);
        addItemDialog.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: if user added a new item, add the new to the table in mainPanel
     */
    public void getItemFromDialog() {
        if (addItemDialog.getNewItem() != null) {
            mainPanel.addRow(addItemDialog.getNewItem());
        }
    }
}
