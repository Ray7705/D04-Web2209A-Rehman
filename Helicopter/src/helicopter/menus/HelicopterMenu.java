package helicopter.menus;
import java.util.Scanner;

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
        options.add("Auto Fly Up");
        options.add("Auto Fly Down");
        options.add("Fly Up");
        options.add("Fly Down");
        options.add("Land");
        options.add("Refuel");
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
                case 4: autoFlyUpHelicopter();
                    break;
                case 5: autoFlyDownHelicopter();
                    break;
                case 6: flyUpHelicopter();
                    break;
                case 7: flyDownHelicopter();
                    break;
                case 8: landHelicopter();
                    break;
                case 9: refuelHelicopter();
                    break;
                case 10:
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

    private void autoFlyUpHelicopter()
    {
        if (helicopter.canFlyToAltitude())
        {
            System.out.println("Auto Pilot Engaged! Flying Up!");
            helicopter.autoFlyUp();
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Auto Pilot Not Engaged! Cannot Fly Up!");
            displayHelicopter();
        }
    }

    private void autoFlyDownHelicopter()
    {
        if (helicopter.canFlyToAltitude())
        {

            System.out.println("Auto Pilot Engaged! Flying Down!");
            helicopter.autoFlyDown();
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Auto Pilot Not Engaged! Cannot Fly Down!");
            displayHelicopter();
        }
    }

    private void flyUpHelicopter()
    {
        if (helicopter.canFlyToAltitude())
        {

            Scanner in = new Scanner(System.in);
            System.out.println("Please Enter the Altitude: ");
            double altitude = in.nextDouble();
            System.out.println("Flying Up!");
            helicopter.flyUp(altitude);
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Cannot Fly Up!");
            displayHelicopter();
        }
    }
    private void flyDownHelicopter()
    {
        if (helicopter.canFlyToAltitude())
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Please Enter the Altitude: ");
            double altitude = in.nextDouble();
            System.out.println("Flying Down!");
            helicopter.flyDown(altitude);
            displayHelicopter();
        }
        else
        {
            System.out.println("Error: Cannot Fly Down!");
            displayHelicopter();
        }
    }

    private void landHelicopter()
    {

        System.out.println("Landed");
        helicopter.land();
        displayHelicopter();
    }
    private void refuelHelicopter()
    {

        System.out.println("Refueled");
        helicopter.refuel();
        displayHelicopter();
    }

    private void displayHelicopter()
    {
        System.out.println(helicopter.getDescription());
    }
}
