package edu.jennifer.hotel.util;

/**
 * @author Khalid
 * @Created 2/18/19 5:01 PM.
 */
public class VisitorCounterHelper {

    private static VisitorCounterHelper instance;
    private static int visitor = 0;
    public static VisitorCounterHelper getInstance() {
        if (instance == null) {
            instance = new VisitorCounterHelper();
        }
        return instance;
    }


    public synchronized void increment() {
       visitor = visitor > 1000 ? visitor = 0 : ++visitor;
       try{
           Thread.sleep(150);
       }catch (Exception ex){}

    }
}
