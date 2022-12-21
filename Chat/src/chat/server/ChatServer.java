package chat.server;

public class ChatServer
{
    public static void main(String[] args)
    {
        Server server = new Server(7777);
        server.start();
    }
}
