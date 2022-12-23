package bath;

import bath.network.BathServer;

/**
 * Do not modify this class.
 * @author Jared Chevalier
 */
public final class Server
{
    public static void main(String[] args)
    {
        BathServer server = new BathServer(7777);
        server.start();
    }
}
