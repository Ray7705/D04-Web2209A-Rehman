package bath.events;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class FaucetChangedEvent implements Serializable
{
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SS");

    private final int id;
    private final boolean running;
    private final Instant time;

    public FaucetChangedEvent(int id, boolean running)
    {
        this.id = id;
        this.running = running;
        time = Instant.now();
    }

    @Override
    public String toString()
    {
        return timeFormatter.format(time.atOffset(ZoneOffset.UTC))
                + ": Bath " + id + " faucet changed: "
                + (faucetIsRunning() ? "Faucet started running water" : "Faucet stopped running water");
    }

    public int getId()
    {
        return id;
    }

    public boolean faucetWasRunning()
    {
        return !running;
    }

    public boolean faucetIsRunning()
    {
        return running;
    }

    public Instant getTime()
    {
        return time;
    }
}
