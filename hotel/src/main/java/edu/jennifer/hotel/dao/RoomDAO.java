package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.model.RoomType;

import java.util.ArrayList;
import java.util.List;

public interface RoomDAO {

	ArrayList<Room> findAll(long randomDelay);

	ArrayList<Room> findFeatured();

	Room findById(int roomId, long randomDelay);

	long toMySqlSeconds(long value);

	ArrayList<RoomType> findAllRoomTypes();

    ArrayList<Room> findByType(int type);

    RoomType getTypeByType(String type);

}
