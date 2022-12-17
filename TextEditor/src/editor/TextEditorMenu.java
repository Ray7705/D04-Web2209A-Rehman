package editor;

import utility.console.ConsoleInput;
import utility.console.ConsoleMenu;

import java.io.*;
import java.util.ArrayList;

public final class TextEditorMenu
{
    private final ConsoleMenu menu;
    private final String directoryPath;
    private final String filePath;

    public TextEditorMenu()
    {
        String title = "Text editor";
        String message = "Select an option:";

        ArrayList<String> options = new ArrayList<>();
        options.add("Write to a file");
        options.add("Read from a file");
        options.add("Exit");

        menu = new ConsoleMenu(title, message, options);
        directoryPath = "files/";
        filePath = directoryPath + "text.txt";
    }

    public void start()
    {
        while (true)
        {
            int selection = menu.displayAndGetSelection();

            switch (selection)
            {
                case 1:     writeToFile();      break;
                case 2:     readFromFile();     break;
                case 3:     return;
            }

            System.out.println();
        }
    }

    private void writeToFile()
    {
        try
        {
            File file = createOrGetFile();
            writeToFile(file);
        }
        catch (IOException e)
        {
            System.out.println("Failed to create file: " + filePath);
            e.printStackTrace();
        }
    }

    private File createOrGetFile() throws IOException
    {
        // Create parent directories if they don't already exist
        File directory = new File(directoryPath);
        boolean createdDirectory = directory.mkdirs();

        // Create file if it doesn't already exist
        File file = new File(filePath);
        boolean createdFile = file.createNewFile();

        if (createdDirectory || createdFile)
            System.out.println("Created file: " + filePath);

        return file;
    }

    private void writeToFile(File file)
    {
        try (FileWriter writer = new FileWriter(file))
        {
            System.out.println("Opened file: " + filePath);
            System.out.println("Enter text to write into the file, or enter 'quit' to return to the menu.");
            System.out.println();

            while (true)
            {
                String line = ConsoleInput.getLine();

                if (line.equalsIgnoreCase("quit"))
                    return;

                writer.write(line);
                writer.write('\n');
                writer.flush();
            }
        }
        catch (IOException e)
        {
            System.out.println("Failed to open or write to file: " + filePath);
            e.printStackTrace();
        }
    }

    private void readFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            System.out.println("Opened file: " + filePath);
            System.out.println();

            while (true)
            {
                String line = reader.readLine();

                if (line == null)
                    return;

                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            System.out.println("Failed to open or read from file: " + filePath);
            e.printStackTrace();
        }
    }
}
