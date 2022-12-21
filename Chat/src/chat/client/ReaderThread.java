package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReaderThread extends Thread
{
    // TODO: BufferedReader field (for reading from server)
    private final BufferedReader reader;

    public ReaderThread(Socket socket) throws IOException
    {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        try {
            while (true){
                String message = reader.readLine();

                // If message is not null, then display it in the console
                if (message != null) {
                    System.out.println(message);
                } else {
                    // Otherwise, return
                    return;
                }
            }
        }catch (IOException e){

        }
    }
}
