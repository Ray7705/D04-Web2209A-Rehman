package helicopter.helicopters;

public class Helicopter
{
    private static int nextId = 10001;

    private final int id;
    private double fuelLevel;
    private double altitude;
    private boolean engineRunning;

    public Helicopter()
    {
        id = nextId++;
        fuelLevel = getMaxFuelLevel();
        altitude = getMinAltitude();
        engineRunning = false;
    }

    @Override
    public String toString()
    {
        return "Helicopter #" + id;
    }

    public String getDescription()
    {
        return toString()
                + ": " + (isEngineRunning() ? "Engine on" : "Engine off")
                + ", " + "Altitude = " + altitude
                + ", " + "Fuel level = " + fuelLevel;
    }

    //getters
    public int getId()
    {
        return id;
    }

    public double getFuelLevel()
    {
        return fuelLevel;
    }

    public double getAltitude()
    {
        return altitude;
    }


    //min max
    public final double getMinFuelLevel()
    {
        return 0;
    }

    public double getMaxFuelLevel()
    {
        return 100;
    }

    public final double getMinAltitude()
    {
        return 0;
    }

    public double getMaxAltitude()
    {
        return 5000;
    }


    //is
    public boolean isEngineRunning()
    {
        return engineRunning;
    }
    public boolean isFuelEmpty()
    {
        return fuelLevel <= getMinFuelLevel();
    }
    public boolean isFuelFull()
    {
        return fuelLevel <= getMaxFuelLevel();
    }

    public boolean isLanded()
    {
        return altitude <= getMinAltitude();
    }
    public boolean isFlying()
    {
        return altitude > getMinAltitude();
    }


    //start stop engine
    public boolean canStartEngine()
    {
        return !engineRunning
                && isLanded()
                && !isFuelEmpty();
    }
    public void startEngine()
    {
        if (canStartEngine())
        {
            engineRunning = true;
        }
    }

    public boolean canStopEngine()
    {
        return engineRunning
                && isLanded();
    }

    public void stopEngine()
    {
        if (canStopEngine())
        {
            engineRunning = false;
        }
    }

}
