package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Facility;
import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.util.HotelExceptions;

import java.util.ArrayList;

public interface RoomDAO {

	public ArrayList<Room> findAll(long randomDelay);

	public ArrayList<Room> findFeatured();
	
	public ArrayList<Room> findByGuestSize(int guestSize, long randomDelay);
	
	public Room findById(int roomId, long randomDelay);

	public ArrayList<Facility> getRoomFacilities(int id);
	
	public long toMySqlSeconds(long value);
	
	public String checkRoom(int id) throws HotelExceptions;
}
