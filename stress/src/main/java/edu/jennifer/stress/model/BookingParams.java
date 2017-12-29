package edu.jennifer.stress.model;

import edu.jennifer.stress.model.GuestInfo;
import edu.jennifer.stress.model.PaymentInfo;

public class BookingParams {

	private String roomId;
	private GuestInfo guestInfo;
	private PaymentInfo paymentInfo;
	private String checkIn;
	private String checkOut;
	private String guestsNumber;
	private String days;
	private String bookingNumber;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
	public String getGuestsNumber() {
		return guestsNumber;
	}
	public void setGuestsNumber(String guestsNumber) {
		this.guestsNumber = guestsNumber;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public void setGuestInfo(GuestInfo guestInfo) {
		this.guestInfo = guestInfo;
	}
	public GuestInfo getGuestInfo() {
		return guestInfo;
	}
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
}
