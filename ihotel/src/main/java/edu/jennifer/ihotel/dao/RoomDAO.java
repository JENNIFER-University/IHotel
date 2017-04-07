package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.Facility;
import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.util.HotelExceptions;

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
