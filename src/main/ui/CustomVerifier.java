package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// represents a subclass of InputVerifier used to validate user inputs
public class CustomVerifier extends InputVerifier {

    private String message;
    private String title;

    /*
     * This method calls the appropriate validation checks for different components
     * MODIFIES: this
     * EFFECTS: if component is an amount field
     *            - returns lengthCheck() and amountCheck()
     *          otherwise if component is a date field
     *            - returns lengthCheck() and dateCheck()
     *
     *          if component is a JTextField
     *            - returns lengthCheck()
     *          otherwise, return false
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: try parsing dateInput to LocalDate
     *          if DateTimeParseException caught
     *            - pass validation error information to setErrorDetails()
     *            - return false
     *          otherwise, return true
     */
    public boolean dateCheck(JTextField dateInput) {
        try {
            LocalDate.parse(dateInput.getText());
        } catch (DateTimeParseException e) {
            setErrorDetails("Invalid Date Format", dateInput.getName() + " Field has an invalid format."
                    + "\nDates must be entered in yyyy-MM-dd.");
            return false;
        }
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the user has entered a value for textField
     *            - return true
     *          otherwise,
     *            - pass validation error information to setErrorDetails()
     *            - return false
     */
    public boolean lengthCheck(JTextField textField) {
        if (textField.getText().trim().length() > 0) {
            return true;
        }
        setErrorDetails("Invalid Length", textField.getName() + " Field has no value assigned.");
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: try parsing amountInput to double
     *          if NumberFormatException caught
     *            - pass validation error information to setErrorDetails()
     *            - return false
     *          otherwise if amount > 0
     *            - return true
     *          otherwise,
     *            - pass validation error information to setErrorDetails()
     *            - return false
     */
    public boolean amountCheck(JTextField amountInput) {
        double amount;
        try {
            amount = Double.parseDouble(amountInput.getText());
        } catch (NumberFormatException e) {
            setErrorDetails("Invalid Number Format", amountInput.getName() + " Field has an invalid input.");
            return false;
        }
        if (amount > 0) {
            return true;
        }
        setErrorDetails("Invalid Range", amountInput.getName() + " Field has to be greater than 0.");
        return false;
    }


    public void setErrorDetails(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if component has an invalid input, call displayError()
     *          return verify(component)
     */
    @Override
    public boolean shouldYieldFocus(JComponent component) {
        boolean isValid = verify(component);
        if (!isValid) {
            displayError(title, message);
        }
        return isValid;
    }

    /*
     * EFFECTS: displays an error message with details about which validation test failed
     */
    public void displayError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
