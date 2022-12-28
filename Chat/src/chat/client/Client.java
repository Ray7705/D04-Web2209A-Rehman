package chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client
{
    private final String host;
    private final int port;

    public Client(String host, int port)
    {
        // TODO: Initialize fields equal to parameters
        this.host = Objects.requireNonNull(host);
        this.port = port;
    }

    public void start()
    {
        try
        {
            Socket socket = new Socket(host, port);

            WriterThread writer = new WriterThread(socket);
            ReaderThread reader = new ReaderThread(socket);

            writer.start();
            reader.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
