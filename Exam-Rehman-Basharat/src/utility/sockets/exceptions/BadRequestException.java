package utility.sockets.exceptions;

import utility.sockets.messages.BadRequestResponse;

/**
 * @author Jared Chevalier
 */
public final class BadRequestException extends Exception
{
    private final BadRequestResponse response;

    public BadRequestException(BadRequestResponse response)
    {
        super(response.getMessage());
        this.response = response;
    }

    public BadRequestResponse getResponse()
    {
        return response;
    }
}
