package ui;

import javax.swing.*;

class CustomCellEditor extends DefaultCellEditor {

    private final CustomVerifier verifier;

    public CustomCellEditor(JTextField textField) {
        super(textField);
        verifier = new CustomVerifier();
    }

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
