package edu.jennifer.ihotel.action.elproblemo;

/**
 * Created by khalid on 07/04/2017.
 */
public class SynchronizeThread implements Runnable{

    private Object object1;
    private Object object2;


    public SynchronizeThread(Object object1, Object object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (object1) {
            System.out.println(name + " acquired lock on Object1: " + object1);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (object2) {
                System.out.println(name + " acquired lock on Object2: " + object2);
            }
            System.out.println(name + " released lock on Object2: " + object2);
        }
        System.out.println(name + " released lock on Object1: " + object1);
    }
}
