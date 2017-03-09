package edu.jennifer.check.launcher;

import edu.jennifer.check.service.CheckCardImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * iCheck Launcher class used to startup the server
 * Created by khalid on 9/7/16.
 */
public class AppLauncher {

    /**
     * Default listening port
     */
    public static final int PORT = 1099;

    public static void main(String[] args) {
        int portNumber = 0;
        if(args.length > 0){
            try{
                portNumber = Integer.parseInt(args[0]);
            }catch (NumberFormatException ex){
                System.out.printf("Invalid port number using default [%d]\n",PORT);
                portNumber = PORT;
            }
        }else{
            portNumber = PORT;
        }

        new AppLauncher().startServer(portNumber);
    }

    /**
     * Start the server and await connections
     * @param port port number to listen to
     */
    private void startServer(int port){
        try{
            System.out.printf("Starting the server and listening on port [%d]\n",port);
            Registry registry = LocateRegistry.createRegistry(port);
            CheckCardImpl checkCard = new CheckCardImpl();
            registry.bind("CheckCard",checkCard);
            System.out.println("Server has started and ready for business .... ");
        }catch (Exception ex){
            System.err.println("Error while starting app the server. Reason [" + ex.getMessage() +"]");
            ex.printStackTrace();
        }
    }
}
