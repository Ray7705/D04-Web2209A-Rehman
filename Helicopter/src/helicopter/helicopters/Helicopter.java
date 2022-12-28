package helicopter.helicopters;
import java.util.Random;

public class Helicopter
{
    private static int nextId = 10001;

    private final int id;
    private double fuelLevel;
    private double altitude;
    private boolean engineRunning;
    private boolean exploded;

    public Helicopter()
    {
        id = nextId++;
        fuelLevel = getMaxFuelLevel();
        altitude = getMinAltitude();
        engineRunning = false;
        exploded = false;
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
                + ", " + "Fuel level = " + fuelLevel
                + (exploded ? " (Exploded)" : " (Functional)");
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
    public double getFuelRate()
    {
        return 1.0 / 100.0;
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
    public boolean isExploded()
    {
        return exploded;
    }


    //start stop engine
    public boolean canStartEngine()
    {
        return !engineRunning
                && isLanded()
                && !isFuelEmpty()
                && !exploded;
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
                && isLanded()
                && !exploded;
    }

    public void stopEngine()
    {
        if (canStopEngine())
        {
            engineRunning = false;
        }
    }

    public static double randomDistance() {
        Random rand = new Random();
        return rand.nextInt(101) + 400;
    }
    public double autoFlyUp()
    {
        double altitude;
        altitude = getAltitude() + randomDistance();

        if (altitude >= getMaxAltitude()){
            altitude =  getMaxAltitude();
        }

        return altitude;
    }
    public double autoFlyDown()
    {
        double altitude;
        altitude = Math.abs(getAltitude() - randomDistance());
        if (altitude <= getMinAltitude()){
            altitude = getMinAltitude();
        }
        return altitude;

    }
    public double flyUp(double altitude)
    {
        double alt;
        alt = getAltitude() + altitude;
        if (alt >= getMaxAltitude()){
            alt = getMaxAltitude();
        }
        return alt;
    }
    public boolean canRefuel(double amount)
    {
        return !engineRunning
                && !exploded
                && isLanded()
                && !isFuelFull()
                && amount > 0;
    }

    public double flyDown(double altitude)
    {
        double alt;
        alt = Math.abs(getAltitude() - altitude);
        if (alt <= getMinAltitude()){
            alt = getMinAltitude();
        }
        return alt;
    }
    public boolean canFlyToAltitude()
    {
        return
                getAltitude() < getMaxAltitude()
                && isFlying()
                && isLanded()
                && !isFuelEmpty()
                && isEngineRunning();

    }
    public void flyToAltitude(double altitude)
    {
        if (canFlyToAltitude()){
            flyUp(altitude);
            flyDown(altitude);
            autoFlyUp();
            autoFlyDown();

        }
    }

    public void fuelConsumption(double altitude)
    {
        if (altitude % 100 == 0){
            fuelLevel--;
        }
    }
    public boolean canExplode(){
        return isFlying()
                && isFuelEmpty();
    }
    public void explode()
    {
        if (canExplode())
        {
            engineRunning = false;
        }
    }

    public void land()
    {
        this.altitude = getMinAltitude();
    }

    public void refuel()
    {
        this.fuelLevel = getMaxFuelLevel();
    }
    protected void crash()
    {
        crashAndExplode();
    }
    protected final void crashAndExplode()
    {
        engineRunning = false;
        altitude = getMinAltitude();
        exploded = true;
    }

    protected final void crashWithoutExploding()
    {
        engineRunning = false;
        altitude = getMinAltitude();
    }

}
