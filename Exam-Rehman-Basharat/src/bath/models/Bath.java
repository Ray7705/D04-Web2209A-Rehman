package bath.models;

import bath.exceptions.BathException;
import bath.exceptions.CapacityException;

public final class Bath
{
    // TODO: Declare fields
    private static int nextUserId= 1000001;
    private final int userId;
    private double capacity;
    private boolean waterIsDraining;
    private boolean waterIsRunning;
    private double currentLevel;
    private final double MAX_LEVEL;
    private final double MIN_LEVEL;
    public Bath(double capacity) throws CapacityException
    {
        waterIsDraining = false;
        waterIsRunning = false;
        MIN_LEVEL = 50;
        MAX_LEVEL = 200;
        userId = nextUserId++;
        // TODO: If capacity is invalid (not between 50 and 200 inclusively), throw a capacity exception
        throw new CapacityException(capacity, MIN_LEVEL , MAX_LEVEL);



        // TODO: Create and start a daemon thread to run the updateLevel method
        //  Create a new thread using a method reference to implement Runnable
        //  Set the thread to be a daemon thread (so it won't keep your program running after closing all windows)
        //  Start the thread
    }

    // Method to be executed by level updater thread
    private void updateLevel()
    {
        double offset;
        if (waterIsRunning){
            offset = -16.5 / 2;
        }else {
            offset = 12 / 2;
        }
        setLevel(getLevel() + offset);

        // Sleep thread for half a second
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Handle exception if the thread is interrupted
        }
    }

    private void setLevel(double level)
    {
        // TODO: If level parameter is actually different than current level:
        if (level != this.currentLevel) {
            // Change the current level, making sure it doesn't go outside the valid range
            this.currentLevel = Math.max(Math.min(level, MAX_LEVEL), MIN_LEVEL);

            // Notify each listener of a level changed event


            // If bath is empty or full, automatically stop draining water and stop running water
            if (this.currentLevel == MIN_LEVEL || this.currentLevel == MAX_LEVEL) {
                //stopDrainingWater();
                //stopRunningWater();
            }
        }
    }

    public int getId()
    {
        return userId;
    }

    public double getCapacity()
    {
        return capacity;
    }

    public double getLevel()
    {
        return currentLevel;
    }

    public boolean isDrainingWater()
    {
        return waterIsDraining;
    }

    public boolean isRunningWater()
    {
        return waterIsRunning;
    }

    public boolean isEmpty()
    {
        return this.currentLevel == MIN_LEVEL;
    }

    public boolean isFull()
    {
        return this.currentLevel == MAX_LEVEL;
    }

    public void addListener(IBathListener listener)
    {

    }

    public void removeListener(IBathListener listener)
    {
        // TODO
    }

    public void startDrainingWater() throws BathException
    {
        if (waterIsDraining) {
            throw new BathException("The water is already draining.");
        }
        if (currentLevel == MIN_LEVEL) {
            throw new BathException("The bath is already empty.");
        }
        waterIsDraining = true;


        // TODO: Notify each listener of a drain changed event
    }

    public void stopDrainingWater() throws BathException
    {
        if (!waterIsDraining) {
            throw new BathException("The water is already not draining.");
        }
        waterIsDraining = false;
        // TODO: Notify each listener of a drain changed event
    }

    public void startRunningWater() throws BathException
    {
        if (waterIsRunning) {
            throw new BathException("The water is already running.");
        }
        if (currentLevel == MAX_LEVEL) {
            throw new BathException("The bath is already full.");
        }

        waterIsRunning = true;
        // TODO: Notify each listener of a faucet changed event
    }

    public void stopRunningWater() throws BathException
    {
        if (!waterIsRunning) {
            throw new BathException("The water is already not running.");
        }
        waterIsRunning = false;

        // TODO: Notify each listener of a faucet changed event
    }
}
