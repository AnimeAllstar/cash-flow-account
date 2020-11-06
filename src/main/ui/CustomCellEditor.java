package ui;

import javax.swing.*;

// represents a CellEditor to be used to edit CustomJTable cells
class CustomCellEditor extends DefaultCellEditor {

    private final CustomVerifier verifier;

    public CustomCellEditor(JTextField textField) {
        super(textField);
        verifier = new CustomVerifier();
    }

    /*
     * This method is called when the user ends an edit on a JTable cell
     * EFFECTS: if user input was invalid
     *            - return false (do no allow user to exit cell)
     *          otherwise, notify listeners that the edit is over and return true
     */
    @Override
    public boolean stopCellEditing() {
        if (!verifier.shouldYieldFocus(getComponent())) {
            return false;
        }
        fireEditingStopped();
        return true;
    }

    @Override
    public JTextField getComponent() {
        return (JTextField) super.getComponent();
    }
}
