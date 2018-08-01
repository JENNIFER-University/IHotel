package edu.jennifer.stress.simula;

import edu.jennifer.logger.ILogger;
import edu.jennifer.stress.model.CliParams;

import java.util.HashSet;

/**
 * @author Khalid
 * @Created 12/29/17 11:47 AM.
 */
public class ThreadsController extends Thread {

    private CliParams cliParams;

    public ThreadsController(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public void run() {
        ILogger.info(String.format("Starting Simula with the following parameters: %n%s", cliParams.toString()));;

        String ihotelUrl = getBaseUrl();

        HashSet<String> activeUsers = new HashSet<>();
        int activeUsersNumber = 0;
        for(;;) {
            int neededUsers;
            synchronized (activeUsers) {
                neededUsers = cliParams.getMaxUsers() - activeUsers.size();
            }

            if (neededUsers > 0) {
                String userId = String.format("Login[%d]", activeUsersNumber);
                synchronized (activeUsers) {
                    if(!activeUsers.contains(userId)) {
                        activeUsersNumber++;
                        new VirtualUser(activeUsers, userId, ihotelUrl).start();
                    }
                }

                if ( (activeUsersNumber >= neededUsers) || (activeUsersNumber >= cliParams.getMaxUsers()) ) {
                    activeUsersNumber = 0;
                }
            }

            try { Thread.sleep(1000L);}catch (InterruptedException ex) {}
        }

    }

    private String getBaseUrl() {
        return String.format("http://%s:%d", cliParams.getIhotelIp(), cliParams.getIhotelPort());
    }
}
