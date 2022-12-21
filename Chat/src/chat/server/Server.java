package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Server implements IServer
{
	private final int port;
	private final Hashtable<String, UserThread> users;

	public Server(int port)
	{
		this.port = port;
		users = new Hashtable<>();
	}

	public void start()
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			System.out.println("Listening for clients on port " + port);

			while (true)
			{
				Socket socket = serverSocket.accept();
				UserThread thread = new UserThread(this, socket);
				thread.start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public synchronized String getUniqueUsername(String username)
	{
		int id = 2;
		String uniqueUsername = username;
		while (users.containsKey(uniqueUsername))
			uniqueUsername = username + "-" + id++;
		return uniqueUsername;
	}

	@Override
	public synchronized void addUser(String username, UserThread thread)
	{
		if (username != null && !users.containsKey(username))
		{
			users.put(username, thread);

			broadcast("User connected: " + username);
			broadcast(getConnectedUsersMessage());
		}
	}

	@Override
	public synchronized void removeUser(String username, UserThread thread)
	{
		boolean exists = username != null && users.containsKey(username);
		if (exists)
		{
			users.remove(username);

			broadcast("User disconnected: " + username);
			broadcast(getConnectedUsersMessage());
		}
	}

	@Override
	public synchronized void broadcast(String message)
	{
		System.out.println(message);

		for (UserThread thread : users.values())
		{
			thread.sendMessage(message);
		}
	}

	private String getConnectedUsersMessage()
	{
		if (users.isEmpty())
			return "No users connected";

		StringBuilder builder = new StringBuilder();
		builder.append("Connected users: ").append(users.size()).append(" ");
		appendUsernames(builder);
		return builder.toString();
	}

	private void appendUsernames(StringBuilder builder)
	{
		builder.append("[");

		int index = 0;
		for (String username : users.keySet())
		{
			if (index > 0)
				builder.append(", ");
			builder.append(username);
			index++;
		}

		builder.append("]");
	}
}
