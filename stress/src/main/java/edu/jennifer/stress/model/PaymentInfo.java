package edu.jennifer.stress.model;

import edu.jennifer.stress.Util;

import java.util.Random;

public class PaymentInfo {

	public static final String[] VISA_PREFIX_LIST = new String[] { "45991235","4539","4556", "4916", "4532", "4929", "40240071", "4485", "4716", "45990059" };

	private String cardNumber;
	private String CCV;
	private String expire;
	
	private Random rand;
	
	
	public PaymentInfo(){
		rand = new Random();
		cardNumber = VISA_PREFIX_LIST[rand.nextInt(VISA_PREFIX_LIST.length)];
		for(int i =0; i < 13; i++){
			cardNumber += Util.getRandom(1, 9) + "";
		}
		
		CCV = Util.getRandom(1, 9) + "" + Util.getRandom(1, 9) + "" + Util.getRandom(1, 9);
		
		expire = Util.getRandom(1, 9) + "/2025";
	}
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public String getCCV() {
		return CCV;
	}
	public String getExpire() {
		return expire;
	}
 
}
