package bath;

import bath.controllers.SetupController;
import bath.views.SetupView;
import utility.swing.style.StyleHelper;
import utility.swing.windows.Window;

import javax.swing.*;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class Client
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Client::start);
    }

    private static void start()
    {
        StyleHelper.configureLookAndFeel();

        SetupView view = new SetupView();
        Window window = new Window("Setup", view, false);
        SetupController controller = new SetupController(view, window);
        window.setVisible(true);
    }
}
