package bath.events;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class LevelChangedEvent implements Serializable
{
    private static final DecimalFormat levelFormatter = new DecimalFormat("0.0");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SS");

    private final int id;
    private final double level;
    private final double capacity;
    private final Instant time;

    public LevelChangedEvent(int id, double level, double capacity)
    {
        this.id = id;
        this.level = level;
        this.capacity = capacity;
        time = Instant.now();
    }

    @Override
    public String toString()
    {
        return timeFormatter.format(time.atOffset(ZoneOffset.UTC))
                + ": Bath " + id + " level changed: " + levelFormatter.format(level)
                + " / " + levelFormatter.format(capacity)
                + (bathIsEmpty() ? " (Empty)" : "")
                + (bathIsFull() ? " (Full)" : "");
    }

    public int getId()
    {
        return id;
    }

    public double getLevel()
    {
        return level;
    }

    public double getCapacity()
    {
        return capacity;
    }

    public Instant getTime()
    {
        return time;
    }

    public boolean bathIsEmpty()
    {
        return level <= 0;
    }

    public boolean bathIsFull()
    {
        return level >= capacity;
    }
}
