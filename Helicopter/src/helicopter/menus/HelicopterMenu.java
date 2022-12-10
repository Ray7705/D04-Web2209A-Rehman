package helicopter.menus;

import helicopter.helicopters.Helicopter;
import utility.console.ConsoleMenu;

import java.util.ArrayList;

public class    HelicopterMenu
{
    private final Helicopter helicopter;
    private final ConsoleMenu mainMenu;

    public HelicopterMenu(Helicopter helicopter)
    {
        this.helicopter = helicopter;
        mainMenu = createMainMenu();
    }

    private static ConsoleMenu createMainMenu()
    {
        String title = "Helicopter menu";
        String message = "Select an option:";

        ArrayList<String> options = new ArrayList<>();
        options.add("Start");
        options.add("Stop");
        options.add("Display");
        options.add("Exit");

        return new ConsoleMenu(title, message, options);
    }

    public void start()
    {
        displayHelicopter();

        while (true)
        {
            // Display menu and get selection from user
            int selection = mainMenu.displayAndGetSelection();

            // Handle the user's request based on their selection
            switch (selection)
            {
                case 1: startHelicopter();
                    break;
                case 2: stopHelicopter();
                    break;
                case 3: displayHelicopter();
                    break;
                case 4:
                    return;


            }
        }
    }

    private void startHelicopter()
    {
        if (helicopter.canStartEngine())
        {
            System.out.println("Starting engine");
            helicopter.startEngine();
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Cannot start engine");
            displayHelicopter();
        }
    }

    private void stopHelicopter()
    {
        if (helicopter.canStopEngine())
        {
            System.out.println("Turning off engine");
            helicopter.stopEngine();
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Cannot turn off engine");
            displayHelicopter();
        }
    }
    private void displayHelicopter()
    {
        System.out.println(helicopter.getDescription());
    }
}
