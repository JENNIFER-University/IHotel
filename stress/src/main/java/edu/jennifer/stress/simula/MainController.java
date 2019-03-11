package edu.jennifer.stress.simula;

import edu.jennifer.common.Logger;
import edu.jennifer.stress.lock.LockMain;
import edu.jennifer.stress.model.CliParams;

import java.sql.Time;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Khalid
 * @Created 12/29/17 11:47 AM.
 */
public class MainController{

    private CliParams cliParams;

    public MainController(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    public void start() {
        Logger.info(String.format("Starting Simula with the following parameters: %n%s", cliParams.toString()));;
        String iHotelUrl = getBaseUrl();

        LockMain lockMainTask = new LockMain(iHotelUrl);
        Timer timer = new Timer();
        long period = 1000 * 60 * 5;
        timer.scheduleAtFixedRate(lockMainTask, period, period);


        ExecutorService executorService = Executors.newFixedThreadPool(10);

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
                        VirtualUser virtualUser = new VirtualUser(activeUsers, userId, iHotelUrl);
                        executorService.submit(virtualUser);
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
