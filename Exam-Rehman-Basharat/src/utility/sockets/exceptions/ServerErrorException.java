package utility.sockets.exceptions;

import utility.sockets.messages.ServerErrorResponse;

/**
 * @author Jared Chevalier
 */
public final class ServerErrorException extends Exception
{
    private final ServerErrorResponse response;

    public ServerErrorException(ServerErrorResponse response)
    {
        super(response.toString());
        this.response = response;
    }

    public ServerErrorResponse getResponse()
    {
        return response;
    }
}
