package edu.jennifer.check.launcher;

import edu.jennifer.check.service.CheckCardImpl;

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
    public static final int PORT = 28080;

    public static void main(String[] args) {
        int portNumber = 0;
        if(args.length > 0){
            try{
                portNumber = Integer.parseInt(args[0]);
            }catch (NumberFormatException ex){
                System.out.printf("[%s] Invalid port number using default [%d]%n",RunCheck.class.getSimpleName(), PORT);
                portNumber = PORT;
            }
        }else{
            portNumber = PORT;
        }

        RunCheck app = new RunCheck();
        app.startServer(portNumber);

    }

    /**
     * Start the server and await connections
     * @param port port number to listen to
     */
    private void startServer(int port){
        try{
            System.out.printf("[%s] Starting the server and listening on port [%d]%n",RunCheck.class.getSimpleName(), port);
            Registry registry = LocateRegistry.createRegistry(port);
            CheckCardImpl checkCard = new CheckCardImpl();
            registry.bind("CheckCard",checkCard);
            System.out.printf("[%s] Server has started and ready for business .... %n", RunCheck.class.getSimpleName());
        }catch (Exception ex){
            System.err.printf("[%s] Error while starting app the server. Reason [%s]%n", RunCheck.class.getSimpleName(), ex.getMessage());
            ex.printStackTrace();
        }
    }

}
