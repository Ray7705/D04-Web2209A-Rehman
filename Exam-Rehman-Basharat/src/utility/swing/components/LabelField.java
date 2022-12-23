package utility.swing.components;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jared Chevalier
 */
public final class LabelField extends JPanel
{
    private final JLabel label;
    private final JLabel value;

    public LabelField(String labelText, String valueText)
    {
        label = createLabel(labelText);
        value = createValue(valueText);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(label);
        add(LayoutHelper.createRigidArea());
        add(value);
    }

    private static JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    private static JLabel createValue(String text)
    {
        JLabel label = new JLabel(text);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
        return label;
    }

    public JLabel getLabel()
    {
        return label;
    }

    public JLabel getValue()
    {
        return value;
    }
}
