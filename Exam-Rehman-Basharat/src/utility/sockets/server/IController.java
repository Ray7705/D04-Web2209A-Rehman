package utility.sockets.server;

import utility.sockets.messages.Request;
import utility.sockets.messages.Response;

/**
 * @author Jared Chevalier
 */
public interface IController
{
    Response handleRequest(Request request);
}
