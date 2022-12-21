package chat.server;

public interface IServer
{
    String getUniqueUsername(String username);
    void addUser(String username, UserThread thread);
    void removeUser(String username, UserThread thread);
    void broadcast(String message);
}
