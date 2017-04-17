package edu.jennifer.stress;

import java.util.Random;

public class Util {

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
		if(m< 9)
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
	

	public static String getCreditCardNumber(boolean fake){
		String cardNumber = "";
		if(fake)
			cardNumber = "500";
		else{
			cardNumber = Util.getRandom(1, 9) + "" + Util.getRandom(1, 9) + "" + Util.getRandom(1, 9); 
		}
			
		for(int i =0; i < 13; i++){
			cardNumber += Util.getRandom(1, 9) + "";
		}
		
		return cardNumber;
	}
}
