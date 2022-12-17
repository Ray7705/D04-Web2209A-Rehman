package utility.console;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class ConsoleMenu
{
    private final String title;
    private final String message;
    private final ArrayList<String> options;

    public ConsoleMenu(String title, String message, ArrayList<String> options)
    {
        this.title = Objects.requireNonNull(title);
        this.message = Objects.requireNonNull(message);
        this.options = Objects.requireNonNull(options);
    }

    @Override
    public String toString()
    {
        return "Console menu: " + title;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMessage()
    {
        return message;
    }

    public String getOption(int index)
    {
        if (index >= 0 && index < options.size())
            return options.get(index);
        return null;
    }

    public int getSize()
    {
        return options.size();
    }

    public int displayAndGetSelectionIndex()
    {
        return displayAndGetSelection() - 1;
    }

    public int displayAndGetSelection()
    {
        displayMenu();
        int selection = getSelection();
        System.out.println();

        return selection;
    }

    private void displayMenu()
    {
        displaySeparator();
        displayTitle();
        displayOptions();
        displayMessage();
    }

    private void displaySeparator()
    {
        System.out.println("-------------------------");
    }

    private void displayTitle()
    {
        if (title.length() > 0)
        {
            System.out.println(title);
            System.out.println();
        }
    }

    private void displayMessage()
    {
        if (message.length() > 0)
        {
            System.out.println(message);
            System.out.println();
        }
    }

    private void displayOptions()
    {
        for (int i = 0; i < options.size(); i++)
        {
            System.out.println("    " + (i + 1) + " - " + options.get(i));
        }
        System.out.println();
    }

    private int getSelection()
    {
        return ConsoleInput.getInt(1, options.size());
    }
}
