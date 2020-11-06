package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomVerifier extends InputVerifier {

    private String message;
    private String title;

    @Override
    public boolean verify(JComponent component) {
        if (component.getName().equals("Amount")) {
            return lengthCheck((JTextField) component) && amountCheck((JTextField) component);
        } else if (component.getName().equals("Date")) {
            return lengthCheck((JTextField) component) && dateCheck((JTextField) component);
        }
        if (component instanceof JTextField) {
            return lengthCheck((JTextField) component);
        }
        return false;
    }

    public boolean dateCheck(JTextField dateInput) {
        try {
            LocalDate.parse(dateInput.getText());
        } catch (DateTimeParseException e) {
            setMessage("Invalid Date Format", dateInput.getName() + " Field has an invalid format."
                    + "\nDates must be entered in yyyy-MM-dd.");
            return false;
        }
        return true;
    }


    public boolean lengthCheck(JTextField textField) {
        if (textField.getText().trim().length() > 0) {
            return true;
        } else {
            setMessage("Invalid Length", textField.getName() + " Field has no value assigned.");
            return false;
        }
    }

    public boolean amountCheck(JTextField amountInput) {
        double amount;
        try {
            amount = Double.parseDouble(amountInput.getText());
        } catch (NumberFormatException e) {
            setMessage("Invalid Number Format", amountInput.getName() + " Field has an invalid input.");
            return false;
        }
        if (amount > 0) {
            return true;
        } else {
            setMessage("Invalid Range", amountInput.getName() + " Field has to be greater than 0.");
            return false;
        }
    }

    public void setMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public boolean shouldYieldFocus(JComponent component) {
        boolean isValid = verify(component);
        if (!isValid) {
            displayError(title, message);
        }
        return isValid;
    }

    public void displayError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
