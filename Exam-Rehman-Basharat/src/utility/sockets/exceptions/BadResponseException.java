package utility.sockets.exceptions;

/**
 * @author Jared Chevalier
 */
public final class BadResponseException extends Exception
{
    private final Object response;

    public BadResponseException(String message)
    {
        this(null, message);
    }

    public BadResponseException(Object response, String message)
    {
        super(message);
        this.response = response;
    }

    public Object getResponse()
    {
        return response;
    }
}
