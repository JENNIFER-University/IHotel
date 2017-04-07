package edu.jennifer.ihotel.action.elproblemo;

import edu.jennifer.ihotel.action.BaseAction;

/**
 * Created by khalid on 07/04/2017.
 */
public class DeadLockAction  extends BaseAction{

    private String threadName;
    private String wait;

    @Override
    public String execute() throws Exception {
        boolean shouldWait;
        try{
            shouldWait = Boolean.valueOf(getWait());
        }catch(Exception ex){
            shouldWait = false;
        }

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();

        Thread synchronizeThread1 = new Thread(new SynchronizeThread(obj1, obj2), threadName+"1");
        Thread synchronizeThread2 = new Thread(new SynchronizeThread(obj2, obj3), threadName+"2");
        Thread synchronizeThread3 = new Thread(new SynchronizeThread(obj3, obj1), threadName+"3");

        synchronizeThread1.start();
        Thread.sleep(3000);
        synchronizeThread2.start();
        Thread.sleep(3000);
        synchronizeThread3.start();

        if(shouldWait){
        	try{
        		while(synchronizeThread1.getState() == Thread.State.BLOCKED)
        			Thread.sleep(3000);
        	}catch(InterruptedException ex){}
        }


        return SUCCESS;
    }

    public String getThreadName() {
        if(threadName == null || threadName.length() == 0) {
            setThreadName("DearLockThread");
        }
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public String getWait() {
        return wait;
    }
}
