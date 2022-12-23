package utility.swing.components;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.event.FocusEvent;

/**
 * @author Jared Chevalier
 */
public final class ComboBoxField<T> extends JPanel
{
    private final JLabel label;
    private final JComboBox<T> comboBox;

    public ComboBoxField(String labelText, T[] comboBoxItems)
    {
        this(labelText, comboBoxItems, null);
    }

    public ComboBoxField(String labelText, T[] comboBoxItems, T selectedItem)
    {
        label = new JLabel(labelText);
        comboBox = new JComboBox<>(comboBoxItems);
        comboBox.setSelectedItem(selectedItem);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(label);
        add(LayoutHelper.createRigidArea());
        add(comboBox);
    }

    public JLabel getLabel()
    {
        return label;
    }

    public JComboBox<T> getComboBox()
    {
        return comboBox;
    }

    public T getSelectedItem()
    {
        return (T) comboBox.getSelectedItem();
    }

    public int getSelectedIndex()
    {
        return comboBox.getSelectedIndex();
    }

    public int getItemCount()
    {
        return comboBox.getItemCount();
    }

    public void setSelectedItem(T item)
    {
        comboBox.setSelectedItem(item);
    }

    public void setSelectedIndex(int index)
    {
        comboBox.setSelectedIndex(index);
    }

    public void clearSelectedItem()
    {
        comboBox.setSelectedItem(null);
    }

    @Override
    public boolean requestFocusInWindow()
    {
        return comboBox.requestFocusInWindow();
    }

    @Override
    public boolean requestFocusInWindow(FocusEvent.Cause cause)
    {
        return comboBox.requestFocusInWindow();
    }
}
