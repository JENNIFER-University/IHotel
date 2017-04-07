package edu.jennifer.ihotel.action.elproblemo;

import edu.jennifer.ihotel.action.BaseAction;

import java.util.Date;

/**
 * Created by khalid on 07/04/2017.
 */
public class HighCpuAction extends BaseAction{

    private long duration;
    private int threadsNumber;


    @Override
    public String execute() throws Exception {
        try{
            int threadsNumber = getThreadsNumber();
            System.out.printf("Using [%d] Threads\n", threadsNumber);
            while(threadsNumber != 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf("[%s] Started to stress CPU\n",getThreadNameAndTime());
                        long now = System.currentTimeMillis();
                        long start = now;
                        long end = start + duration;
                        while (now < end) {
                            now = System.currentTimeMillis();
                            Math.atan(Math.sqrt(Math.pow(100, 10)));
                            Math.log(System.currentTimeMillis());
                        }

                        end = System.currentTimeMillis() - start;
                        System.out.printf("\n[%s] Stressed CPU for [%d] ms.", getThreadNameAndTime(), end);
                    }
                }).start();
                threadsNumber--;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return SUCCESS;
    }


    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    public int getThreadsNumber() {
        if(threadsNumber == 0) {
            this.setThreadsNumber(8);
        }
        return threadsNumber;
    }

    /**
     //	 * Helper function to get the current thread name and current time as string
     //	 * @return Time and Thread name Format DATE : THREAD_NAME
     //	 */
	private String getThreadNameAndTime(){
		return new Date().toString() + ": " + Thread.currentThread().getName();
	}

}
