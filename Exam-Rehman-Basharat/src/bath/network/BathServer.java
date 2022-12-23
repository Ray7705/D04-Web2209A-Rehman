package bath.network;

import bath.events.DrainChangedEvent;
import bath.events.FaucetChangedEvent;
import bath.events.LevelChangedEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class BathServer
{
    private final int port;

    public BathServer(int port)
    {
        this.port = port;
    }

    public void start()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            while (true)
            {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(() -> handleClient(socket));
                thread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket)
    {
        try (ObjectInputStream reader = new ObjectInputStream(socket.getInputStream()))
        {
            Object object = reader.readObject();

            if (object instanceof DrainChangedEvent event)
            {
                drainChanged(event);
            }
            else if (object instanceof FaucetChangedEvent event)
            {
                faucetChanged(event);
            }
            else if (object instanceof LevelChangedEvent event)
            {
                levelChanged(event);
            }
            else if (object != null)
            {
                System.err.println("Server expected a LevelChangedEvent, but received an object of unexpected type: " + object.getClass().getSimpleName());
            }
            else
            {
                System.err.println("Server expected a LevelChangedEvent, but received null");
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            tryCloseSocket(socket);
        }
    }

    private void drainChanged(DrainChangedEvent event)
    {
        System.out.println(event);
    }

    private void faucetChanged(FaucetChangedEvent event)
    {
        System.out.println(event);
    }

    private void levelChanged(LevelChangedEvent event)
    {
        System.out.println(event);
    }

    private void tryCloseSocket(Socket socket)
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
}
