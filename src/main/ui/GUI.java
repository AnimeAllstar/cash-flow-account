package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    JFrame frame;
    MainPanel newContentPane;

    public GUI() {
        frame = new JFrame("GUI");
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
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Save Changes");
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Revert Changes");
        menuItem.setActionCommand("revert");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
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
