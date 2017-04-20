package plugin.cpu;

import java.util.Date;

/**
 * Created by khalid on 4/19/17.
 */
public class CPUStressThread extends Thread {

    private long duration;

    public CPUStressThread(long duration) {
        this.duration = duration;
    }

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

    private String getThreadNameAndTime(){
        return new Date().toString() + ": " + Thread.currentThread().getName();
    }
}
