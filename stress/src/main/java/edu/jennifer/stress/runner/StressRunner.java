package edu.jennifer.stress.runner;

import edu.jennifer.stress.model.CliParams;

import java.util.HashSet;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 12/29/17 11:47 AM.
 */
public class StressRunner extends Thread {

    private CliParams cliParams;

    public StressRunner(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public void run() {
        System.out.printf("Starting Stress with the following paramters: %n%s%n", cliParams.toString());

        String ihotelUrl = getBaseUrl();

        HashSet<String> activeUsers = new HashSet<>();
        int activeUsersNumber = 0;
        for(;;) {
            int neededUsers;
            synchronized (activeUsers) {
                neededUsers = cliParams.getMaxUsers() - activeUsers.size();
            }

            if (neededUsers > 0) {
                String userId = String.format("User[%d]", activeUsersNumber);
                synchronized (activeUsers) {
                    if(!activeUsers.contains(userId)) {
                        activeUsersNumber++;
                        new VirtualUser(activeUsers, userId, ihotelUrl, cliParams.isDebug()).start();
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
        return String.format("http://%s:%d/ihotel", cliParams.getIhotelIp(), cliParams.getIhotelPort());
    }
}
