package bath.models;

public final class Bath
{
    // TODO: Declare fields

    public Bath(double capacity)
    {
        // TODO: If capacity is invalid (not between 50 and 200 inclusively), throw a capacity exception

        // TODO: Initialize fields

        // TODO: Create and start a daemon thread to run the updateLevel method
        //  Create a new thread using a method reference to implement Runnable
        //  Set the thread to be a daemon thread (so it won't keep your program running after closing all windows)
        //  Start the thread
    }

    // Method to be executed by level updater thread
    private void updateLevel()
    {
        // TODO: Repeatedly, while water is draining and/or running:
        //  Calculate level offset (amount to increase or decrease per half second)
        //      If draining, amount drained per second = 16.5
        //      If running, amount filled per second = 12
        //  Call setter method to set level based on current level and calculated offset
        //  Sleep thread for half a second (500 milliseconds)
    }

    private void setLevel(double level)
    {
        // TODO: If level parameter is actually different than current level:
        //  Change the current level, making sure that it doesn't go outside the valid range
        //  Notify each listener of a level changed event
        //  If bath is empty or full, automatically stop draining water and stop running water
    }

    public int getId()
    {
        // TODO
        return 0;
    }

    public double getCapacity()
    {
        // TODO
        return 0;
    }

    public double getLevel()
    {
        // TODO
        return 0;
    }

    public boolean isDrainingWater()
    {
        // TODO
        return false;
    }

    public boolean isRunningWater()
    {
        // TODO
        return false;
    }

    public boolean isEmpty()
    {
        // TODO
        return false;
    }

    public boolean isFull()
    {
        // TODO
        return false;
    }

    public void addListener(IBathListener listener)
    {
        // TODO
    }

    public void removeListener(IBathListener listener)
    {
        // TODO
    }

    public void startDrainingWater()
    {
        // TODO: If water is already draining, throw a bath exception with a user-friendly error message
        // TODO: If bath is empty, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a drain changed event
    }

    public void stopDrainingWater()
    {
        // TODO: If water is already not draining, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a drain changed event
    }

    public void startRunningWater()
    {
        // TODO: If water is already running, throw a bath exception with a user-friendly error message
        // TODO: If bath is full, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a faucet changed event
    }

    public void stopRunningWater()
    {
        // TODO: If water is already not running, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a faucet changed event
    }
}
