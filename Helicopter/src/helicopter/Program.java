package helicopter;

import helicopter.helicopters.Helicopter;
import helicopter.menus.HelicopterMenu;

public class Program
{
    public static void main(String[] args)
    {
        Helicopter helicopter = new Helicopter();
        HelicopterMenu menu = new HelicopterMenu(helicopter);

        menu.start();
    }
}
