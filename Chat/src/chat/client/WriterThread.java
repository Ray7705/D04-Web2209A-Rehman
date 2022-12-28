package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class WriterThread extends Thread
{


    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public WriterThread(Socket socket) throws IOException
    {
        this.socket = Objects.requireNonNull(socket);
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String username = readUsername();
            writer.println(username);
            writer.flush();

            while (true) {
                String message = reader.readLine();
                writer.println(message);
                writer.flush();

                if (message.equalsIgnoreCase("quit"))
                    return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tryCloseSocket();
        }
    }


    private String readUsername() throws IOException
    {
        while (true)
        {
            System.out.println("Enter username:");
            String username = reader.readLine();
            if (username != null && !username.isBlank())
                return username;

            System.out.println("Error: Username should not be empty.");
        }
    }

    private void tryCloseSocket()
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
