package edu.jennifer.stress.model;

import edu.jennifer.common.AppUtil;

public class PaymentInfo {

	public static final String[] VISA_PREFIX_LIST = new String[] { "45991235","4539","4556", "4916", "4532", "4929", "40240071", "4485", "4716", "45990059" };

	private String cardNumber;
	private String CCV;
	private String expire;

	public static PaymentInfo createPaymentInfo() {
		PaymentInfo paymentInfo = new PaymentInfo();
		String cardNumber = VISA_PREFIX_LIST[AppUtil.getRandomBounded(VISA_PREFIX_LIST.length)];
		for(int i =0; i < 13; i++){
			cardNumber += AppUtil.getRandom(1, 9) + "";
		}

		paymentInfo.setCardNumber(cardNumber);

		paymentInfo.setCCV(AppUtil.getRandom(1, 9) + "" + AppUtil.getRandom(1, 9) + "" + AppUtil.getRandom(1, 9));

		paymentInfo.setExpire(AppUtil.getRandom(1, 9) + "/2025");

		return paymentInfo;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCCV(String CCV) {
		this.CCV = CCV;
	}
	public String getCCV() {
		return CCV;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getExpire() {
		return expire;
	}
 
}
