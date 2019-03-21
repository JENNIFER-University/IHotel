package edu.jennifer.check.launcher;

import edu.jennifer.check.service.CheckCardImpl;
import edu.jennifer.common.Logger;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * iCheck Launcher class used to startup the server
 * Created by khalid on 9/7/16.
 */
public class RunCheck {

    /**
     * Default listening port
     */
    private static final int PORT = 28080;

    /**
     * Start the server and await connections
     * @param port port number to listen to
     */
    private void startServer(int port){
        try{
            Logger.info(String.format("Starting the server and Listening on Port [%d]", port));
            Registry registry = LocateRegistry.createRegistry(port);
            CheckCardImpl checkCard = new CheckCardImpl();
            registry.bind("CheckCard",checkCard);
            Logger.info("Server has started and ready for business");
        }catch (Exception ex){
            Logger.error("Error while starting up the server", ex);
        }
    }

    public static void main(String[] args) {
        int portNumber;
        if(args.length > 0){
            try{
                portNumber = Integer.parseInt(args[0]);
            }catch (NumberFormatException ex){
                Logger.warn(String.format("Invalid port number. Using default port [%d]", PORT));
                portNumber = PORT;
            }
        }else{
            portNumber = PORT;
        }

        new RunCheck().startServer(portNumber);
    }

}
