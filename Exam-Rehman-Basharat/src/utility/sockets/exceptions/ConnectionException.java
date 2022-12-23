package utility.sockets.exceptions;

import java.io.IOException;

/**
 * @author Jared Chevalier
 */
public final class ConnectionException extends Exception
{
    private final IOException cause;

    public ConnectionException(String message, IOException cause)
    {
        super(message, cause);
        this.cause = cause;
    }

    @Override
    public IOException getCause()
    {
        return cause;
    }
}
