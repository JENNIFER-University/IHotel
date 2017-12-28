package edu.jennifer.hotel.model;

public class Guest {

	private int id;
	private String title;
	private String forName;
	private String sureName;
	private String dateOfBirth;
	private String address;
	private String phone;
	private String email;
	private int bookingCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getForName() {
		return forName;
	}
	public void setForName(String forName) {
		this.forName = forName;
	}
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBookingCount(int bookingCount) {
		this.bookingCount = bookingCount;
	}
	public int getBookingCount() {
		return bookingCount;
	}
	
	
	
}
