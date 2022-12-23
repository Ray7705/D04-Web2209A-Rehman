package bath.events;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class DrainChangedEvent implements Serializable
{
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SS");

    private final int id;
    private final boolean draining;
    private final Instant time;

    public DrainChangedEvent(int id, boolean draining)
    {
        this.id = id;
        this.draining = draining;
        time = Instant.now();
    }

    @Override
    public String toString()
    {
        return timeFormatter.format(time.atOffset(ZoneOffset.UTC))
                + ": Bath " + id + " drain changed: "
                + (drainIsDraining() ? "Drain started draining water" : "Drain stopped draining water");
    }

    public int getId()
    {
        return id;
    }

    public boolean drainWasDraining()
    {
        return !draining;
    }

    public boolean drainIsDraining()
    {
        return draining;
    }

    public Instant getTime()
    {
        return time;
    }
}
