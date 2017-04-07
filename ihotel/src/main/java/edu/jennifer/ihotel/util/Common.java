package edu.jennifer.ihotel.util;

import edu.jennifer.ihotel.dao.ReservationDAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Common {

	public static final String APP_VERSION = "1.1";

	public static String getCurrentTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getDateFromTimeStamp(String timeStamp){
		String y = timeStamp.substring(0,4);
		String m = timeStamp.substring(4,6);
		String d = timeStamp.substring(6,8);
		
		return y + "-" + m + "-" + d;
	}
	
	public static String getDateFromTimeStampAsString(String timeStamp){
		String y = timeStamp.substring(0,4);
		int m = Integer.parseInt(timeStamp.substring(4,6));
		String monthName = new DateFormatSymbols().getMonths()[m-1];
		String d = timeStamp.substring(6,8);
		
		return d + " " +  monthName + ", " + y;
	}
	
	
	public static long getTotalDays(String d1, String d2){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date inD = sdf.parse(d1);
			Date ouD = sdf.parse(d2);
			
			if(ouD.getTime() < inD.getTime()){
				return -1;
			}
			
			long diff = ouD.getTime() - inD.getTime();
			long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			
			return days;
		}catch(Exception ex){
			return -1;
		}
	}
	
	public static int getRandom(int low,int high){
		high++;
		int max = high - low;
		int value = Math.abs(new Random().nextInt()) & max;
		return value + low;
	}

	/**
	 * Create Delay
	 * @param napTime Sleep Time in milliseconds
	 */
	public static void logTime(long napTime){
		try{
			Thread.sleep(napTime);
		}catch(Exception ex){}

	}
	
	public static String reservationToString(int reservationCode){
		switch (reservationCode) {
		case ReservationDAO.BOOKED:
			return "<span class='alert-danger'><strong>PENDING PAYMENT</strong></span>";
		case ReservationDAO.PAYED:
			return "<span class='alert-success'><strong>BOOKING COMPLETED</strong></span>";
		case ReservationDAO.UNKIWN:
			return "<span class='alert-danger'><strong>UNKOWN</strong></span>";
		default:
			return "<span class='alert-danger'><strong>UNKOWN</strong></span>";
		}
		
	}
	
	public static String getRandomName(){
		String[] firstName = {"Khalid","David","Alex","Sami","Sally","Dana","Chris","Chalse","Albert"};
		String[] lastName  = {"Zhang","Robert","Saeed","Andy","Justin","Craig","Grant","Bert","Lester"};
		int firstNameIndex = (int) Math.random() * firstName.length;
		int lastNameIndex = (int) Math.random() * lastName.length;
		String name = firstName[firstNameIndex] + " " + lastName[lastNameIndex];
		return name;
	}
	
	public static String maskCreditCard(String cardNumber){
		String masked = "";
		if(cardNumber.length() > 4){
			String last4 = cardNumber.substring(cardNumber.length() - 4);
			for(int i =0 ; i < cardNumber.length() - 4; i++){
				masked += "*";
			}
			masked = masked + last4;
		}else{
			int lastn = cardNumber.length() / 2;
			String lastNS = cardNumber.substring(cardNumber.length() - lastn);
			for(int i = 0; i < lastn; i++){
				masked += "*";
			}
			masked = masked + lastNS;
		}
		
		return masked;
	}

	public static String plainToMD5(String text){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes(), 0, text.length());
			return new BigInteger(1,md.digest()).toString(16);
		}catch(NoSuchAlgorithmException ex){
			return text;
		}
	}
		
	public static String getToken(long zz){
		String token = getCurrentTimeStamp();
		logTime(zz);
		return token;
	}
	
	private static long longtx_next = System.currentTimeMillis() + getRandom(10, 60)* 1000;
	
	public static boolean processLong(int next_min, int next_max, int time){
		//every minute Approximately
		long now = System.currentTimeMillis();
		if(now < longtx_next)
			return false;
		longtx_next = now + getRandom(next_min, next_max) * 1000;
		try{
			Thread.sleep(time * 1000);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
}
