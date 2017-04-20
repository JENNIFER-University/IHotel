package edu.jennifer.ihotel.model;

public class Reservation {

	private String id;
	private Guest guest;
	private Room room;
	private String checkIn;
	private String checkOut;
	private int guestNumbers;
	private int status;
	private Payment paymentDetails;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public int getGuestNumbers() {
		return guestNumbers;
	}
	public void setGuestNumbers(int guestNumbers) {
		this.guestNumbers = guestNumbers;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setPaymentDetails(Payment paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public Payment getPaymentDetails() {
		return paymentDetails;
	}
	
	
}
