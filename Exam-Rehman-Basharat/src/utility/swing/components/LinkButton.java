package utility.swing.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * @author Jared Chevalier
 */
public final class LinkButton extends JButton
{
    private final Color defaultColor = new Color(100, 116, 204);
    private final Color rolloverColor = new Color(10, 149, 255);

    public LinkButton()
    {
        super();
        initialize();
    }

    public LinkButton(String text)
    {
        super(text);
        initialize();
    }

    public LinkButton(Icon icon)
    {
        super(icon);
        initialize();
    }

    public LinkButton(String text, Icon icon)
    {
        super(text, icon);
        initialize();
    }

    public LinkButton(Action action)
    {
        super(action);
        initialize();
    }

    private void initialize()
    {
        setAlignmentX(0.5f);
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setOpaque(false);
        setForeground(defaultColor);
        setBackground(new Color(0, 0, 0, 0));
        addChangeListener(this::onChange);
    }

    private void onChange(ChangeEvent listener)
    {
        boolean rollover = model.isRollover();
        Cursor cursor = rollover ? new Cursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor();
        Color color = rollover ? rolloverColor : defaultColor;
        Integer underlineValue = rollover ? TextAttribute.UNDERLINE_ON : null;

        Font font = getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, underlineValue);
        font = font.deriveFont(attributes);

        setCursor(cursor);
        setForeground(color);
        setFont(font);
    }
}
