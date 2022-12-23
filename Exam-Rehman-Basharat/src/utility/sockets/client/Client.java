package utility.sockets.client;

import utility.sockets.exceptions.BadRequestException;
import utility.sockets.exceptions.BadResponseException;
import utility.sockets.exceptions.ConnectionException;
import utility.sockets.exceptions.ServerErrorException;
import utility.sockets.messages.BadRequestResponse;
import utility.sockets.messages.Request;
import utility.sockets.messages.Response;
import utility.sockets.messages.ServerErrorResponse;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * @author Jared Chevalier
 */
public final class Client
{
    private final String host;
    private final int port;

    public Client(String host, int port)
    {
        this.host = Objects.requireNonNull(host);
        this.port = port;
    }

    public <TResponse extends Response> TResponse sendRequest(Request request, Class<TResponse> responseClass) throws BadRequestException, ServerErrorException, BadResponseException, ConnectionException
    {
        Response response = sendRequest(request);

        try
        {
            return responseClass.cast(response);
        }
        catch (ClassCastException e)
        {
            throw new BadResponseException(response, "Unexpected response: " + response.getClass().getName() + " cannot be cast to " + responseClass.getName());
        }
    }

    public Response sendRequest(Request request) throws BadRequestException, ServerErrorException, BadResponseException, ConnectionException
    {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream reader = new ObjectInputStream(socket.getInputStream()))
        {
            sendRequest(request, writer);
            return receiveResponse(reader);
        }
        catch (IOException e)
        {
            String message = "Failed to connect to server: " + e.getClass().getName() + ": " + e.getMessage();
            System.out.println(message);
            throw new ConnectionException(message, e);
        }
    }

    private static void sendRequest(Request request, ObjectOutputStream writer) throws ConnectionException
    {
        try
        {
            writer.writeObject(request);
            writer.flush();

            System.out.println("Client sent request: " + request);
        }
        catch (IOException e)
        {
            String message = "Failed to send request to server: " + e.getClass().getName() + ": " + e.getMessage();
            System.out.println(message);
            throw new ConnectionException(message, e);
        }
    }

    private static Response receiveResponse(ObjectInputStream reader) throws BadRequestException, ServerErrorException, BadResponseException, ConnectionException
    {
        try
        {
            Object object = reader.readObject();
            if (object instanceof Response response)
            {
                System.out.println("Client received response: " + response);

                if (response instanceof BadRequestResponse badRequestResponse)
                    throw new BadRequestException(badRequestResponse);

                if (response instanceof ServerErrorResponse serverErrorResponse)
                    throw new ServerErrorException(serverErrorResponse);

                return response;
            }
            else if (object != null)
            {
                String message = "Unexpected response: " + object.getClass().getName() + " cannot be cast to " + Response.class.getName();
                displayBadResponseMessage(message);
                throw new BadResponseException(object, message);
            }
            else
            {
                String message = "Unexpected response: Null response.";
                displayBadResponseMessage(message);
                throw new BadResponseException(message);
            }
        }
        catch (ClassNotFoundException e)
        {
            String message = "Class not found: " + e.getMessage();
            displayBadResponseMessage(message);
            throw new BadResponseException(message);
        }
        catch (InvalidClassException e)
        {
            String message = "Invalid class: " + e.classname;
            displayBadResponseMessage(message);
            throw new BadResponseException(message);
        }
        catch (OptionalDataException e)
        {
            String message = "Invalid response data.";
            displayBadResponseMessage(message);
            throw new BadResponseException(message);
        }
        catch (IOException e)
        {
            String message = "Failed to receive response from server: " + e.getClass().getName() + ": " + e.getMessage();
            System.out.println(message);
            throw new ConnectionException(message, e);
        }
    }

    private static void displayBadResponseMessage(String message)
    {
        System.out.println("Client received bad response: " + message);
    }
}
