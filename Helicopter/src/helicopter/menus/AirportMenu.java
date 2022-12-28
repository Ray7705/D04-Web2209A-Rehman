package helicopter.menus;

import helicopter.helicopters.AttackHelicopter;
import helicopter.helicopters.Helicopter;
import utility.console.ConsoleMenu;

import java.util.ArrayList;
import java.util.Random;

public class AirportMenu {

    private static final Random random = new Random();

    private final ArrayList<Helicopter> helicopters;
    private final ConsoleMenu mainMenu;

    public AirportMenu()
    {
        helicopters = new ArrayList<>();
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());

        mainMenu = createMainMenu();
    }

    private static Helicopter createRandomHelicopter()
    {
        if (random.nextBoolean())
            return new Helicopter();
        else
            return new AttackHelicopter();
    }

    private static ConsoleMenu createMainMenu()
    {
        String title = "Airport menu";
        String message = "Select an option:";

        ArrayList<String> options = new ArrayList<>();
        options.add("Add helicopter");
        options.add("Add attack helicopter");
        options.add("Remove helicopter");
        options.add("Control helicopter");
        options.add("Land all helicopters");
        options.add("Display all helicopters");
        options.add("Exit");

        return new ConsoleMenu(title, message, options);
    }

    public void start()
    {
        displayAllHelicopters();

        while (true)
        {
            // Display menu and get selection from user
            int selection = mainMenu.displayAndGetSelection();

            // Handle the user's request based on their selection
            switch (selection)
            {
                case 1:     addHelicopter();            break;
                case 2:     addAttackHelicopter();      break;
                case 3:     removeHelicopter();         break;
                case 4:     controlHelicopter();        break;
                case 5:     landAllHelicopters();       break;
                case 6:     displayAllHelicopters();    break;
                case 7:     return;
            }
        }
    }

    private void addHelicopter()
    {
        helicopters.add(new Helicopter());
        displayAllHelicopters();
    }

    private void addAttackHelicopter()
    {
        helicopters.add(new AttackHelicopter());
        displayAllHelicopters();
    }

    private void removeHelicopter()
    {
        ConsoleMenu menu = createHelicoptersSubmenu("Remove helicopter");
        int index = menu.displayAndGetSelectionIndex();
        if (index < helicopters.size())
        {
            helicopters.remove(index);
            displayAllHelicopters();
        }
    }

    private void controlHelicopter()
    {
        ConsoleMenu menu = createHelicoptersSubmenu("Control helicopter");
        int index = menu.displayAndGetSelectionIndex();
        if (index < helicopters.size())
        {
            Helicopter helicopter = helicopters.get(index);
            HelicopterMenu helicopterMenu = new HelicopterMenu(helicopter);
            helicopterMenu.start();
        }
    }

    private ConsoleMenu createHelicoptersSubmenu(String title)
    {
        String message = "Select a helicopter:";

        ArrayList<String> options = new ArrayList<>();
        for (Helicopter helicopter : helicopters)
            options.add(helicopter.toString());
        options.add("Cancel");

        ConsoleMenu menu = new ConsoleMenu(title, message, options);
        return menu;
    }

    private void landAllHelicopters()
    {
        System.out.println("Landing all helicopters");

        for (Helicopter helicopter : helicopters)
        {
            helicopter.flyToAltitude(helicopter.getMinAltitude());
        }

        displayAllHelicopters();
    }

    private void displayAllHelicopters()
    {
        for (Helicopter helicopter : helicopters)
        {
            System.out.println(helicopter.getDescription());
        }
    }



}
