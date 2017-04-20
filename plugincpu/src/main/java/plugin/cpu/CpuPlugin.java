package plugin.cpu;

import edu.jennifer.pluginmanager.Plugin;

import java.util.HashMap;

/**
 * Created by khalid on 4/19/17.
 */
public class CpuPlugin implements Plugin {

    private HashMap<String, Object> options;

    final int THREADS_NUMBER = 8;
    final long DEFAULT_DURATION = 10000;

    @Override
    public String getName() {
        return "CPU Plugin";
    }

    @Override
    public String getVersion() {
        return "V1.0";
    }

    @Override
    public void setOptions(HashMap<String, Object> options) {
        this.options = options;
    }

    @Override
    public void run() {
        int threadNumbers;
        long duration;

        if(options == null || options.size() == 0) {
            threadNumbers = THREADS_NUMBER;
            duration = DEFAULT_DURATION;
        }else{
            try{
                threadNumbers = (int)options.get("threads");
            }catch (Exception ex) {
                threadNumbers = THREADS_NUMBER;
            }

            try{
                duration = (int)options.get("duration");
            }catch (Exception ex) {
                duration = DEFAULT_DURATION;
            }
        }



        System.out.printf("Using [%d] Threads\n", threadNumbers);
        while(threadNumbers != 0) {
            new CPUStressThread(duration).start();
            threadNumbers--;
        }
    }


}
