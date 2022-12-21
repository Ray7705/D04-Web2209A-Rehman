package chat.client;

public class ChatClient
{
    public static void main(String[] args)
    {
        Client client = new Client("localhost", 7777);
        client.start();
    }
}
