package edu.jennifer.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author Khalid
 * @Created 7/24/18 12:08 PM.
 */
public class AppUtil {

    private final static Random RANDOM = new Random();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Get Random number
     * @param low
     * @param high
     * @return
     */
    public static int getRandom(int low,int high){
        high++;
        int max = high - low;
        int value = Math.abs(RANDOM.nextInt()) % max;
        return value + low;
    }

    /**
     * Get Random Number
     * @param bound
     * @return
     */
    public static int getRandomBounded(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * Add N days to current date and get result as formatted string (MM/dd/yyyy)
     * @param addedDays additional days to be added to TODAY
     * @return
     */
    public static String addDaysAndGetDateFormatted(int addedDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, addedDays);
        return sdf.format(calendar.getTime());
    }

    /**
     * Current Timestamp as string
     * @return
     */
    public static String getCurrentTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * Return current date as String (yyyy-MM-dd)
     * @return
     */
    public static String getCurrentDateAsString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * Create Random Name
     * @return
     */
    public static String getRandomName(){
        String[] firstName = {"Khalid","David","Alex","Sami","Sally","Dana","Chris","Chalse","Albert"};
        String[] lastName  = {"Zhang","Robert","Saeed","Andy","Justin","Craig","Grant","Bert","Lester"};
        int firstNameIndex = (int) (Math.random() * firstName.length);
        int lastNameIndex = (int) (Math.random() * lastName.length);
        return firstName[firstNameIndex] + " " + lastName[lastNameIndex];
    }
}
