package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WriterThread extends Thread
{
    // TODO: Socket field
    Socket socket = new Socket("localhost" , 7777);

    // TODO: PrintWriter field (for writing to server)
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    // TODO: BufferedReader field (for reading from System.in console)
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public WriterThread(Socket socket) throws IOException
    {
        // TODO: Store socket parameter in field
        // TODO: Initialize writer field by creating a PrintWriter with socket's output stream
        // TODO: Initialize reader field by creating a BufferedReader that reads from System.in
    }

    @Override
    public void run()
    {

        try {
            System.out.println("Enter Your Username: ");
            String username = consoleReader.readLine();

            out.println(username);
            while (true){
                System.out.print("Enter your message: ");
                String message = consoleReader.readLine();

                // Send message to server
                out.println(message);

                // If message equals "quit", then return
                if (message.equals("quit")) {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }
}
