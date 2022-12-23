package bath.views;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class SetupView extends JPanel
{
    private final JTextField capacityTextField;
    private final JButton createButton;

    public SetupView()
    {
        this.capacityTextField = new JTextField(6);
        this.createButton = new JButton("Create");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        add(createCapacityPanel());
        add(LayoutHelper.createRigidArea());
        add(createButtonPanel());
    }

    private JPanel createCapacityPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("Bath capacity"));
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(capacityTextField);
        return panel;
    }

    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(createButton);
        return panel;
    }

    public void addCreateListener(ActionListener listener)
    {
        createButton.addActionListener(listener);
    }

    public void removeCreateListener(ActionListener listener)
    {
        createButton.removeActionListener(listener);
    }

    public JButton getDefaultButton()
    {
        return createButton;
    }

    public String getCapacity()
    {
        return capacityTextField.getText();
    }

    public void resetCapacity()
    {
        capacityTextField.setText("");
    }

    public void displayError(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
    }
}
