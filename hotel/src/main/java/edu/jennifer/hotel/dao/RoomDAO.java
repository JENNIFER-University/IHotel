package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Room;

import java.util.ArrayList;

public interface RoomDAO {

	ArrayList<Room> findAll(long randomDelay);

	ArrayList<Room> findFeatured();

	Room findById(int roomId, long randomDelay);

	long toMySqlSeconds(long value);

}
