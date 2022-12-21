package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread
{
	private final IServer server;
	private final Socket socket;
	private final BufferedReader reader;
	private final PrintWriter writer;

	public UserThread(IServer server, Socket socket) throws IOException
	{
		this.server = server;
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(socket.getOutputStream(), true);
	}

	public void sendMessage(String message)
	{
		writer.println(message);
		writer.flush();
	}

	@Override
	public void run()
	{
		String username = null;
		try
		{
			username = reader.readLine();

			if (username != null)
			{
				username = addUniqueUser(username);
				processMessages(username);
			}
		}
		catch (IOException e)
		{ }
		finally
		{
			server.removeUser(username, this);
			closeSocket();
		}
	}

	private String addUniqueUser(String username)
	{
		synchronized (server)
		{
			username = server.getUniqueUsername(username);
			server.addUser(username, this);
			return username;
		}
	}

	private void processMessages(String username) throws IOException
	{
		while (true)
		{
			String message = reader.readLine();
			if (message == null || message.equalsIgnoreCase("quit"))
				return;

			server.broadcast("[" + username + "]: " + message);
		}
	}

	private void closeSocket()
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