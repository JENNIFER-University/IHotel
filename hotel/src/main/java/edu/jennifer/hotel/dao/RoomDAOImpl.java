package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Facility;
import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.model.RoomType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl extends BaseDao implements RoomDAO{


	public RoomDAOImpl(DataSource ds){
		super(ds);
	}

	/**
	 * Find all rooms
	 * @return
	 */
	public ArrayList<Room> findAll() {
		try{
			float randomDelay = mySqlDelay();
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize,SLEEP(?)"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id";

			return jdbcTemplate.query(query, new Object[]{randomDelay}, this::extractDataFromReultSet);
		}catch(Exception ex){
			return null;
		}
	}

	/**
	 * Find featured rooms (only find with limit of 4)
	 * @return
	 */
	public ArrayList<Room> findFeatured() {
		try{
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id"
					+ " limit 4";

			return jdbcTemplate.query(query, this::extractDataFromReultSet);
		}catch(Exception ex){
			return null;
		}
	}

	/**
	 * Get All RoomTypes
	 * @return
	 */
	public ArrayList<RoomType> findAllRoomTypes() {
		try {
			String query = "select id,roomType,roomSize,maxCapacity from rooms_type";
			return jdbcTemplate.query(query, resultSet -> {
                ArrayList<RoomType> result = new ArrayList<>();

                while(resultSet.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(resultSet.getInt("id"));
                    roomType.setMaxCapacity(resultSet.getInt("maxCapacity"));
                    roomType.setRoomSize(resultSet.getString("roomSize"));
                    roomType.setRoomType(resultSet.getString("roomType"));
                    result.add(roomType);
                }
                return result;
            });
		}catch (Exception sql){
		    return null;
		}
	}


	/**
	 * Find a room by type
	 * @param type
	 * @return
	 */
	public ArrayList<Room> findByType(int type) {
		try {
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id where rooms.room_type = ?";

			return jdbcTemplate.query(query, new Object[]{type}, this::extractDataFromReultSet);
		}catch (Exception ex) {
			return null;
		}
	}

	/**
	 * find a Type by name
	 * @param name
	 * @return
	 */
	public RoomType findTypeByName(String name) {
		try {
			String query = "select id,roomType,roomSize,maxCapacity from rooms_type where roomType = ?";
			return jdbcTemplate.query(query, new Object[]{name} , resultSet -> {

                if(resultSet.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(resultSet.getInt("id"));
                    roomType.setMaxCapacity(resultSet.getInt("maxCapacity"));
                    roomType.setRoomSize(resultSet.getString("roomSize"));
                    roomType.setRoomType(resultSet.getString("roomType"));
                    return roomType;
                }
                return null;
            });
		}catch (Exception sql){
			return null;
		}
	}

	/**
	 * Find room by ID
	 * @param roomId
	 * @return
	 */
	public Room findById(int roomId) {
		try{

			float randomDelay = mySqlDelay();
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize,SLEEP(?)"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id"
					+ " where rooms.id = ?";

			Room room = jdbcTemplate.query(query, new Object[]{randomDelay,roomId},new ResultSetExtractor<Room>(){


				public Room extractData(ResultSet rs) throws SQLException,
						DataAccessException {

					if(rs.next()){
						RoomType roomType = new RoomType();
						roomType.setMaxCapacity(rs.getInt("maxCapacity"));
						roomType.setRoomSize(rs.getString("roomSize"));
						roomType.setRoomType(rs.getString("roomType"));

						Room result = new Room();
						result.setId(rs.getInt("id"));
						result.setRoomNumber(rs.getInt("number"));
						result.setFloor(rs.getString("floor"));
						result.setPrice(rs.getDouble("price"));
						result.setDescription(rs.getString("description"));
						result.setRoomType(roomType);

						return result;
					}

					return null;
				}

			});

			if(room != null){
				query = "select rooms.id,rooms.number,facilities.name"
						+ " from rooms"
						+ " left join rooms_facilities"
						+ " on rooms.id = rooms_facilities.roomId"
						+ " left join facilities"
						+ " on facilities.id = rooms_facilities.facilityId"
						+ " where rooms.id = " + roomId;
				ArrayList<Facility> f = jdbcTemplate.query(query, rs -> {
                    ArrayList<Facility> roomFacilities = new ArrayList<>();
                    while(rs.next()){
                        Facility f1 = new Facility();
                        f1.setName(rs.getString("name"));
                        roomFacilities.add(f1);
                    }
                    return roomFacilities;
                });

				if(f != null && f.size() > 0)
					room.setRoomFacilities(f);
				return room;
			}

			return null;
		}catch(Exception ex){
			return null;
		}
	}


	private ArrayList<Room> extractDataFromReultSet(ResultSet rs) throws SQLException {

		ArrayList<Room> result = new ArrayList<Room>();
		while(rs.next()){
			RoomType roomType = new RoomType();
			roomType.setMaxCapacity(rs.getInt("maxCapacity"));
			roomType.setRoomSize(rs.getString("roomSize"));
			roomType.setRoomType(rs.getString("roomType"));

			Room room = new Room();
			room.setId(rs.getInt("id"));
			room.setDescription(rs.getString("description"));
			room.setRoomNumber(rs.getInt("number"));
			room.setFloor(rs.getString("floor"));
			room.setPrice(rs.getDouble("price"));
			room.setRoomType(roomType);

			result.add(room);
		}
		return result;
	}
}
