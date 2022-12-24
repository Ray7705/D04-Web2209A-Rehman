package bath.loggers;

import bath.events.DrainChangedEvent;
import bath.events.FaucetChangedEvent;
import bath.events.LevelChangedEvent;
import bath.models.IBathListener;

import java.io.FileWriter;
import java.io.IOException;

public final class BathLogger implements IBathListener
{
    @Override
    public void drainChanged(DrainChangedEvent event) {
        //  1. Create a file writer, with path "logs/log.txt"
        //  2. Use the file writer to append event.toString() to the end of the log file on a new line
        //  3. Don't forget the two things that must be done after writing to a stream
        try (FileWriter fileWriter = new FileWriter("logs/log.txt", true)){
            fileWriter.append(event.toString() + "\n");
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void faucetChanged(FaucetChangedEvent event) {
        try (FileWriter fileWriter = new FileWriter("logs/log.txt", true)){
            fileWriter.append(event.toString() + "\n");
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  1. Create a file writer, with path "logs/log.txt"
        //  2. Use the file writer to append event.toString() to the end of the log file on a new line
        //  3. Don't forget the two things that must be done after writing to a stream
    }

    @Override
    public void levelChanged(LevelChangedEvent event) {
        // TODO
        //  Implement IBathListener
        //  Log each bath event in a log file:
        //  1. Create a file writer, with path "logs/log.txt"
        //  2. Use the file writer to append event.toString() to the end of the log file on a new line
        //  3. Don't forget the two things that must be done after writing to a stream
        try (FileWriter fileWriter = new FileWriter("logs/log.txt", true)){
            fileWriter.append(event.toString() + "\n");
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
