package edu.jennifer.ihotel.util;

/**
 * @author Khalid Elshafie
 * @Created 9/15/17 3:42 PM.
 */
public class RoomAvailablityCheck implements Runnable {

    private Object object1;
    private Object object2;


    public RoomAvailablityCheck(Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        isRoomAvaiable(name);

    }

    private void isRoomAvaiable(String name) {
        synchronized (object1) {
            System.out.println(name + ": acquired lock on Object " + object1);
            try { Thread.sleep(10000);} catch (InterruptedException ie){}

            synchronized (object2) {
                System.out.println(name + ": acquired lock on Object " + object2);
            }
        }
    }
}
