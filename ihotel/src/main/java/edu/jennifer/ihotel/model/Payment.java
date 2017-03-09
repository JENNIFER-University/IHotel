package edu.jennifer.ihotel.model;

public class Payment {

	private String id;
	private String reservationId;
	private String ammount;
	private String cardNumber;
	private String cardHolder;
	private String expire;
	private String ccv;
	
	public Payment(){}
	
	public Payment(String id, String reservationId, String ammount, String cardNumber, String cardHolder,String expire, String ccv){
		this.id = id;
		this.reservationId = reservationId;
		this.ammount = ammount;
		this.cardNumber = cardNumber;
		this.cardHolder = cardHolder;
		this.expire = expire;
		this.ccv = ccv;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	
	
}
