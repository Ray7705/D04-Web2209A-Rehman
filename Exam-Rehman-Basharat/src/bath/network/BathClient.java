package bath.network;

import bath.events.DrainChangedEvent;
import bath.events.FaucetChangedEvent;
import bath.events.LevelChangedEvent;
import bath.models.IBathListener;

public final class BathClient implements IBathListener
{
    @Override
    public void drainChanged(DrainChangedEvent event) {

    }

    @Override
    public void faucetChanged(FaucetChangedEvent event) {

    }

    @Override
    public void levelChanged(LevelChangedEvent event) {

    }
    // TODO
    //  Implement IBathListener
    //  Send each bath event to the bath server:
    //  1. Connect to bath server using a socket, with host "localhost" and port 7777
    //  2. Use an object output stream to send the event object to the server
    //  3. Don't forget the two things that must be done after writing to a stream
}
