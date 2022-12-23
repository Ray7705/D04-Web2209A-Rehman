package bath.exceptions;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class CapacityException extends BathException
{
    private final double capacity;
    private final double minimum;
    private final double maximum;

    public CapacityException(double capacity, double minimum, double maximum)
    {
        super(createMessage(capacity, minimum, maximum));

        this.capacity = capacity;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    private static String createMessage(double capacity, double minimum, double maximum)
    {
        return "Invalid capacity " + capacity
                + ". Capacity must be between "
                + minimum + " and " + maximum + ".";
    }

    public double getCapacity()
    {
        return capacity;
    }

    public double getMinimum()
    {
        return minimum;
    }

    public double getMaximum()
    {
        return maximum;
    }
}
