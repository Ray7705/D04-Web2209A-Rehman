package utility.swing.windows;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jared Chevalier
 */
public final class Window extends JFrame
{
    private static final Dimension zeroSize = new Dimension(0, 0);

    public Window(String title)
    {
        this(title, new JPanel(), true);
    }

    public Window(String title, Container contentPane)
    {
        this(title, contentPane, true);
    }

    public Window(String title, boolean resizable)
    {
        this(title, new JPanel(), resizable);
    }
    
    public Window(String title, Container contentPane, boolean resizable)
    {
        super(title);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(resizable);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }

    @Override
    public void setContentPane(Container contentPane)
    {
        super.setContentPane(contentPane);
        resizeToFitContent();
    }

    public void resizeToFitContent()
    {
        setMinimumSize(zeroSize);
        pack();
        setMinimumSize(getSize());
    }
}
