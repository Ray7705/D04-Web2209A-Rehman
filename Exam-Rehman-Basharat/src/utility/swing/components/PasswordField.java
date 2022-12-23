package utility.swing.components;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.event.FocusEvent;

/**
 * @author Jared Chevalier
 */
public final class PasswordField extends JPanel
{
    private final JLabel label;
    private final JPasswordField passwordField;

    public PasswordField(String labelText)
    {
        label = new JLabel(labelText);
        passwordField = new JPasswordField(12);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(label);
        add(LayoutHelper.createRigidArea());
        add(passwordField);
    }

    public JLabel getLabel()
    {
        return label;
    }

    public JTextField getPasswordField()
    {
        return passwordField;
    }

    public char[] getPassword()
    {
        return passwordField.getPassword();
    }

    public void clearPassword()
    {
        passwordField.setText("");
    }

    @Override
    public boolean requestFocusInWindow()
    {
        return passwordField.requestFocusInWindow();
    }

    @Override
    public boolean requestFocusInWindow(FocusEvent.Cause cause)
    {
        return passwordField.requestFocusInWindow();
    }
}
