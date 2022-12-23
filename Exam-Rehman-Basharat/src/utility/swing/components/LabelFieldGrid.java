package utility.swing.components;

import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class LabelFieldGrid extends JPanel
{
    private final int fieldCount;
    private final ArrayList<JLabel> fieldLabels;
    private final ArrayList<JLabel> fieldValues;

    public LabelFieldGrid(ArrayList<StringFieldData> fields)
    {
        Objects.requireNonNull(fields);

        fieldCount = fields.size();
        fieldLabels = new ArrayList<>();
        fieldValues = new ArrayList<>();

        setLayout(new GridLayout(fields.size(), 2, LayoutHelper.DefaultSize, LayoutHelper.DefaultSize));

        for (StringFieldData field : fields)
        {
            JLabel fieldLabel = createFieldLabel(field.getLabel());
            fieldLabels.add(fieldLabel);
            add(fieldLabel);

            JLabel fieldValue = createFieldValue(field.getValue());
            fieldValues.add(fieldValue);
            add(fieldValue);
        }
    }

    private static JLabel createFieldLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    private static JLabel createFieldValue(String text)
    {
        JLabel label = new JLabel(text);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
        return label;
    }

    public int getFieldCount()
    {
        return fieldCount;
    }

    public JLabel getFieldLabel(int index)
    {
        return fieldLabels.get(index);
    }

    public JLabel getFieldValue(int index)
    {
        return fieldValues.get(index);
    }
}
