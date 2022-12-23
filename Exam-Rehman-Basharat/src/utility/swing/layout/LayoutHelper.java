package utility.swing.layout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Jared Chevalier
 */
public final class LayoutHelper
{
    public static final int SmallSize = 10;
    public static final int DefaultSize = 20;
    public static final int LargeSize = 40;
    private static final Dimension SmallDimension = new Dimension(SmallSize, SmallSize);
    private static final Dimension DefaultDimension = new Dimension(DefaultSize, DefaultSize);
    private static final Dimension LargeDimension = new Dimension(LargeSize, LargeSize);
    
    private LayoutHelper()
    { }
    
    public static Border createSmallEmptyBorder()
    {
        return BorderFactory.createEmptyBorder(SmallSize, SmallSize, SmallSize, SmallSize);
    }
    
    public static Border createEmptyBorder()
    {
        return BorderFactory.createEmptyBorder(DefaultSize, DefaultSize, DefaultSize, DefaultSize);
    }

    public static Border createEmptyBorder(int size)
    {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }
    
    public static Border createLargeEmptyBorder()
    {
        return BorderFactory.createEmptyBorder(LargeSize, LargeSize, LargeSize, LargeSize);
    }
    
    public static Border addSmallMargin(Border border)
    {
        return BorderFactory.createCompoundBorder(createSmallEmptyBorder(), border);
    }
    
    public static Border addMargin(Border border)
    {
        return BorderFactory.createCompoundBorder(createEmptyBorder(), border);
    }

    public static Border addMargin(Border border, int size)
    {
        return BorderFactory.createCompoundBorder(createEmptyBorder(size), border);
    }
    
    public static Border addLargeMargin(Border border)
    {
        return BorderFactory.createCompoundBorder(createLargeEmptyBorder(), border);
    }
    
    public static Border addSmallPadding(Border border)
    {
        return BorderFactory.createCompoundBorder(border, createSmallEmptyBorder());
    }
    
    public static Border addPadding(Border border)
    {
        return BorderFactory.createCompoundBorder(border, createEmptyBorder());
    }

    public static Border addPadding(Border border, int size)
    {
        return BorderFactory.createCompoundBorder(border, createEmptyBorder(size));
    }
    
    public static Border addLargePadding(Border border)
    {
        return BorderFactory.createCompoundBorder(border, createLargeEmptyBorder());
    }
    
    public static Component createSmallRigidArea()
    {
        return Box.createRigidArea(SmallDimension);
    }
    
    public static Component createRigidArea()
    {
        return Box.createRigidArea(DefaultDimension);
    }

    public static Component createRigidArea(int size)
    {
        return Box.createRigidArea(new Dimension(size, size));
    }
    
    public static Component createLargeRigidArea()
    {
        return Box.createRigidArea(LargeDimension);
    }
}
