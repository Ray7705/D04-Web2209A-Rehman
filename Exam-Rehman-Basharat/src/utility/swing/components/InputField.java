package utility.swing.components;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.event.FocusEvent;

/**
 * @author Jared Chevalier
 */
public final class InputField extends JPanel
{
    private final JLabel label;
    private final JTextField textField;

    public InputField(String labelText)
    {
        label = new JLabel(labelText);
        textField = new JTextField(12);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(label);
        add(LayoutHelper.createRigidArea());
        add(textField);
    }

    public JLabel getLabel()
    {
        return label;
    }

    public JTextField getTextField()
    {
        return textField;
    }

    public String getText()
    {
        return textField.getText();
    }

    public void clearText()
    {
        textField.setText("");
    }

    @Override
    public boolean requestFocusInWindow()
    {
        return textField.requestFocusInWindow();
    }

    @Override
    public boolean requestFocusInWindow(FocusEvent.Cause cause)
    {
        return textField.requestFocusInWindow();
    }
}
