package chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
    String host = "LocalHost";
    int port = 7777;

    public Client(String host, int port)
    {
        // TODO: Initialize fields equal to parameters
    }

    public void start()
    {

        try {
            Socket socket = new Socket(host , port);
            ObjectOutputStream writer = n   ew ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            {
                //  Start the writer thread

                //  Start the reader thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
