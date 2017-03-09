package edu.jennifer.ipayment.model;

public class ReservationDetails {
	
	private String id;
	private String reservation_id;
	private String ammount;
	private String cardnumber;
	private String cardholder;
	private String expire;
	private String ccv;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
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
	
	public static ReservationDetails generate(String reservationId){
		ReservationDetails details = new ReservationDetails();
		details.setId(reservationId);
		details.setReservation_id(reservationId);
		details.setAmmount("125.5");
		details.setExpire("2015-02-12");
		details.setCardholder("Khalid Saeed");
		details.setCardnumber("12345678913");
		details.setCcv("123");
		return details;
	}
}
