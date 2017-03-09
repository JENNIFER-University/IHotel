package edu.jennifer.ihotel.dao;

import java.util.ArrayList;

import edu.jennifer.ihotel.model.Facility;
import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.util.HotelExceptions;

public interface RoomDAO {

	public ArrayList<Room> findAll(long randomDelay) throws Throwable;
	
	public ArrayList<Room> findByGuestSize(int guestSize, long randomDelay);
	
	public Room findById(int roomId, long randomDelay);

	public ArrayList<Facility> getRoomFacilities(int id);
	
	public long toMySqlSeconds(long value);
	
	public String checkRoom(int id) throws HotelExceptions;
}
