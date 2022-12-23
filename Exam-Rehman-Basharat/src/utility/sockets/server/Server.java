package utility.sockets.server;

import utility.sockets.messages.BadRequestResponse;
import utility.sockets.messages.Request;
import utility.sockets.messages.Response;
import utility.sockets.messages.ServerErrorResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Jared Chevalier
 */
public final class Server implements AutoCloseable
{
    private final IController controller;
    private final int port;
    private ServerSocket serverSocket;

    public Server(IController controller, int port)
    {
        this.controller = Objects.requireNonNull(controller);
        this.port = port;
        serverSocket = null;
    }

    public synchronized void start()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            this.serverSocket = serverSocket;
            System.out.println("Server started: Ready to handle requests on port " + port + ".");

            while (true)
            {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(() -> handleClientRequest(socket));
                thread.start();
            }
        }
        catch (SocketException e)
        {
            // Expected exception caused by closing server socket from another thread.
            System.out.println("Server closed.");
        }
        catch (IOException e)
        {
            // Unexpected input/output exception.
            System.out.println("Server closed unexpectedly.");
            e.printStackTrace();
        }
        finally
        {
            this.serverSocket = null;
        }
    }

    private void handleClientRequest(Socket socket)
    {
        try (ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream()))
        {
            try
            {
                Object object = reader.readObject();

                if (object instanceof Request request)
                    handleRequest(writer, request);
                else if (object != null)
                    handleBadRequest(writer, "Unexpected request: " + object.getClass().getName() + " cannot be cast to " + Request.class.getName());
                else
                    handleBadRequest(writer, "Null request.");
            }
            catch (ClassNotFoundException e)
            {
                handleBadRequest(writer, "Class not found: " + e.getMessage());
            }
            catch (InvalidClassException e)
            {
                handleBadRequest(writer, "Invalid class: " + e.classname);
            }
            catch (OptionalDataException e)
            {
                handleBadRequest(writer, "Invalid request data.");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            tryCloseSocket(socket);
        }
    }

    private void handleBadRequest(ObjectOutputStream writer, String message) throws IOException
    {
        System.out.println("Server received bad request: " + message);

        Response response = new BadRequestResponse(message);

        writer.writeObject(response);
        writer.flush();

        System.out.println("Server sent response: " + response);
    }

    private void handleRequest(ObjectOutputStream writer, Request request) throws IOException
    {
        System.out.println("Server received request: " + request);

        Response response = handleRequest(request);

        writer.writeObject(response);
        writer.flush();

        System.out.println("Server sent response: " + response);
    }

    private Response handleRequest(Request request)
    {
        try
        {
            return controller.handleRequest(request);
        }
        catch (Exception e)
        {
            String id = UUID.randomUUID().toString();
            Response response = new ServerErrorResponse(id);

            System.err.println("Unhandled exception: " + e.getClass().getName() + " " + id);
            e.printStackTrace();

            return response;
        }
    }

    private static void tryCloseSocket(Socket socket)
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        try
        {
            ServerSocket serverSocket = this.serverSocket;
            if (serverSocket != null)
                serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
