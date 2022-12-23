package utility.sockets.messages;

import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class ServerErrorResponse extends Response
{
    private final String id;

    public ServerErrorResponse(String id)
    {
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public String toString()
    {
        return "Server error: " + id;
    }

    public String getId()
    {
        return id;
    }
}
