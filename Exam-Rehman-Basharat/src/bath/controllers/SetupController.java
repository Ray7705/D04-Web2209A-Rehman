package bath.controllers;

import bath.exceptions.CapacityException;
import bath.loggers.BathLogger;
import bath.models.Bath;
import bath.network.BathClient;
import bath.views.BathView;
import bath.views.SetupView;
import utility.swing.windows.Window;

import java.awt.event.ActionEvent;

/**
 * Do not modify this class, except for the input event handler method definition.
 * @author Jared Chevalier
 */
public final class SetupController
{
    private final SetupView view;
    private final Window window;

    public SetupController(SetupView view, Window window)
    {
        this.view = view;
        this.window = window;

        view.addCreateListener(this::onCreateClicked);
        window.getRootPane().setDefaultButton(view.getDefaultButton());
    }

    private void onCreateClicked(ActionEvent event)
    {
        try {
            double capacity = Double.parseDouble(view.getCapacity());
            Bath bath = new Bath(capacity);
            BathView bathView = new BathView(bath);
            BathController bathController = new BathController(bath, bathView);

            BathLogger logger = new BathLogger();
            bath.addListener(logger);

            // Create bath client and add it as a listener to the bath model
            BathClient client = new BathClient();
            bath.addListener(client);

            window.setContentPane(bathView);
            window.setVisible(true);
        } catch (CapacityException e) {
            throw new RuntimeException(e);
        }
        // TODO
        //  Create bath model + view + controller
        //  Create bath logger and add it as a bath listener
        //  Create bath client and add it as a bath listener
        //  Display bath view in window (to do so, either reuse current window, or create a new one and close current one)
        //  Handle possible exceptions thrown above by displaying error message in view
    }
}
