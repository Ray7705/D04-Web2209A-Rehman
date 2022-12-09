package utility.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleInput
{
	private static BufferedReader reader;
	
	// Static initializer
	static
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Private constructor
	// So that class is purely static and cannot be instantiated
	private ConsoleInput()
	{ }
	
	
	// Console input methods
	
	public static String readLine() throws IOException
	{
		return reader.readLine();
	}

	public static int readInt() throws IOException, NumberFormatException
	{
		return Integer.parseInt(readLine());
	}

	public static double readDouble() throws IOException, NumberFormatException
	{
		return Double.parseDouble(readLine());
	}

	public static String getLine()
	{
		String line = "";
		
		try
		{
			line = readLine();	
		}
		catch (IOException e)
		{
			System.out.println("Error: Input/output exception: " + e.getMessage());
		}
		
		return line;
	}

	public static String getLine(String message)
	{
		System.out.println(message);
		return getLine();
	}
	
	public static int getInt()
	{
		int number = 0;
		
		try
		{
			while (true)
			{
				try
				{
					number = readInt();
					break; // Break from loop only if readInt() did not throw exception
				}
				catch (NumberFormatException e)
				{
					System.out.println("Error: Invalid integer number format");
				}
			}			
		}
		catch (IOException e)
		{
			System.out.println("Error: Input/output exception: " + e.getMessage());
		}
		
		return number;
	}
	
	public static int getInt(int min, int max)
	{
		int number;
		
		while (true)
		{
			number = getInt();
			if (number < min || number > max)
				System.out.println("Error: " + number + " is outside valid range [" + min + ", " + max + "]");
			else
				break;
		}
		
		return number;
	}
	
	public static int getInt(String message)
	{
		System.out.println(message);
		return getInt();
	}
	
	public static int getInt(String message, int min, int max)
	{
		System.out.println(message);
		return getInt(min, max);
	}

	public static double getDouble()
	{
		double number = 0;
		
		try
		{
			while (true)
			{
				try
				{
					number = readDouble();
					break; // Break from loop only if readDouble() did not throw exception
				}
				catch (NumberFormatException e)
				{
					System.out.println("Error: Invalid double number format");
				}
			}			
		}
		catch (IOException e)
		{
			System.out.println("Error: Input/output exception: " + e.getMessage());
		}
		
		return number;
	}
	
	public static double getDouble(double min, double max)
	{
		double number;
		
		while (true)
		{
			number = getDouble();
			if (number < min || number > max)
				System.out.println("Error: " + number + " is outside valid range [" + min + ", " + max + "]");
			else
				break;
		}
		
		return number;
	}
	
	public static double getDouble(String message)
	{
		System.out.println(message);
		return getDouble();
	}
	
	public static double getDouble(String message, double min, double max)
	{
		System.out.println(message);
		return getDouble(min, max);
	}
}
