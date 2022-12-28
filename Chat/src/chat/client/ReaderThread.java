package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Objects;

public class ReaderThread extends Thread
{
    // TODO: BufferedReader field (for reading from server)
    private final BufferedReader reader;

    public ReaderThread(Socket socket) throws IOException
    {
        Objects.requireNonNull(socket);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String message = reader.readLine();
                if (message != null)
                    System.out.println(message);
                else
                    return;
            }
        }
        catch (IOException e)
        {

        }
    }




}
