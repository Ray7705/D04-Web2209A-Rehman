package utility.sockets.messages;

import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class BadRequestResponse extends Response
{
    private final String message;

    public BadRequestResponse(String message)
    {
        this.message = Objects.requireNonNull(message);
    }

    @Override
    public String toString()
    {
        return "Bad request: " + message;
    }

    public String getMessage()
    {
        return message;
    }
}
