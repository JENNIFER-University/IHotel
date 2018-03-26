package edu.jennifer.stress.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class AppUtil {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private static Random rand = new Random();
	
	public static int getRandom(int min, int max){
		max++;
		int diff = max - min;
		int value = Math.abs(rand.nextInt()) % diff;
		return (value+min);
	}

	/**
	 * Get Date as String (MM/dd/yyyy)
	 * @param addedDays additional days to TODAY
	 * @return
	 */
	public static String getDateFormatted(int addedDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, addedDays);
		return sdf.format(calendar.getTime());
	}
}
