package utility.swing.components;

import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class StringFieldData
{
    private final String label;
    private final String value;

    public StringFieldData(String label, String value)
    {
        this.label = Objects.requireNonNull(label);
        this.value = Objects.requireNonNull(value);
    }

    public String getLabel()
    {
        return label;
    }

    public String getValue()
    {
        return value;
    }
}
