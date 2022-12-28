package helicopter;

import helicopter.helicopters.Helicopter;
import helicopter.menus.AirportMenu;
import helicopter.menus.HelicopterMenu;

public class Program
{
    public static void main(String[] args)
    {
        AirportMenu menu = new AirportMenu();
        menu.start();
    }
}
