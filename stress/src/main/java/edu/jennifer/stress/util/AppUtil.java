package edu.jennifer.stress.util;

import edu.jennifer.stress.model.BookingParams;

import java.util.Random;

public class AppUtil {

	private static Random rand = new Random();
	
	public static int getRandom(int min, int max){
		max++;
		int diff = max - min;
		int value = Math.abs(rand.nextInt()) % diff;
		return (value+min);
	}
	
	public static BookingParams getCheckInOut(){
		BookingParams params = new BookingParams();
		int day 	= getRandom(1, 15);
		int m 		= getRandom(1, 12); 
		String month = "";
		if(m < 9)
			month = "0" + m;
		else
			month = "" + m;
			
		int year 	= 2015;
		
		int days = getRandom(1, 7);
		
		String checkIn = month + "/"+day+"/"+year;
		String checkOut = month  + "/" + (day + days) + "/" +  year;
		
		params.setCheckIn(checkIn);
		params.setCheckOut(checkOut);
		params.setDays(days + "");
		
		return params;
	}
}
