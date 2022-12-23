package bath.models;

import bath.events.DrainChangedEvent;
import bath.events.FaucetChangedEvent;
import bath.events.LevelChangedEvent;

/**
 * Do not modify this interface.
 * @author Jared Chevalier
 */
public interface IBathListener
{
    void drainChanged(DrainChangedEvent event);
    void faucetChanged(FaucetChangedEvent event);
    void levelChanged(LevelChangedEvent event);
}
