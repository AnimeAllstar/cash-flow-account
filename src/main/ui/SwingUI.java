package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingUI implements ActionListener {

    JFrame frame;
    MainPanel newContentPane;

    public SwingUI() {
        frame = new JFrame("SwingUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newContentPane = new MainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);

        frame.setJMenuBar(this.addMenuBar());

        frame.pack();
        frame.setVisible(true);
    }

    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        menuBar.add(toolMenu());
        menuBar.add(analyseMenu());
        return menuBar;
    }

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

    private JMenu toolMenu() {
        JMenu menu = new JMenu("Tools");

        JMenuItem menuItem = new JMenuItem("Add Item");
        menuItem.setActionCommand("add");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menu;
    }

    private JMenu analyseMenu() {
        JMenu menu = new JMenu("Analyse");

        JMenuItem menuItem = new JMenuItem("Pie Chart");
        menuItem.setActionCommand("pieChart");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("revert")) {
            newContentPane.revertChanges();
        } else if (e.getActionCommand().equals("save")) {
            newContentPane.saveChanges();
        }
    }
}
