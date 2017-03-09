package edu.jennifer.ihotel.model;

import java.util.ArrayList;

public class Room {

	private int id;
	private int roomNumber;
	private String floor;
	private double price;
	private String description;
	private RoomType roomType;
	
	private ArrayList<Facility> roomFacilities;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public ArrayList<Facility> getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(ArrayList<Facility> roomFacilities) {
		this.roomFacilities = roomFacilities;
	}
	
	
}
