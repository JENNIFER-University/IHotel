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

public class RoomDAOImpl implements RoomDAO{

	private JdbcTemplate jdbcTempalte;

	public RoomDAOImpl(DataSource ds){
		jdbcTempalte = new JdbcTemplate(ds);
	}


	public ArrayList<Room> findAll(long rd) {
		try{
			long randomDelay = toMySqlSeconds(rd);
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize,SLEEP(?)"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id";

			ArrayList<Room> rooms = jdbcTempalte.query(query, new Object[]{randomDelay},new ResultSetExtractor<ArrayList<Room>>(){


				public ArrayList<Room> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
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

			});

//			LoadUtil.getInstance().makeOOM();

			return rooms;
		}catch(Exception ex){
			return null;
		}
	}

	public ArrayList<Room> findFeatured() {
		try{
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id"
					+ " limit 4";

			ArrayList<Room> rooms = jdbcTempalte.query(query,new ResultSetExtractor<ArrayList<Room>>(){


				public ArrayList<Room> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
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

			});

			return rooms;
		}catch(Exception ex){
			return null;
		}
	}

	public Room findById(int roomId, long rd) {
		try{
			long randomDelay = toMySqlSeconds(rd);
			String query = "select rooms.id,rooms.number,rooms.floor,rooms.price,rooms.description,rooms_type.roomType,rooms_type.maxCapacity,rooms_type.roomSize,SLEEP(?)"
					+ " from rooms"
					+ " left join rooms_type"
					+ " on rooms.room_type = rooms_type.id"
					+ " where rooms.id = ?";

			Room room = jdbcTempalte.query(query, new Object[]{randomDelay,roomId},new ResultSetExtractor<Room>(){


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
				query = "select rooms.id,rooms.number,facilities.facilityName"
						+ " from rooms"
						+ " left join rooms_facilities"
						+ " on rooms.id = rooms_facilities.roomId"
						+ " left join facilities"
						+ " on facilities.id = rooms_facilities.facilityId"
						+ " where rooms.id = " + roomId;
				ArrayList<Facility> f = jdbcTempalte.query(query, new ResultSetExtractor<ArrayList<Facility>>(){


					public ArrayList<Facility> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						ArrayList<Facility> roomFacilities = new ArrayList<Facility>();
						while(rs.next()){
							Facility f = new Facility();
							f.setFacilityName(rs.getString("facilityName"));
							roomFacilities.add(f);
						}
						return roomFacilities;
					}

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

	public long toMySqlSeconds(long value) {
		long result = (value / 1000) / 4;
		return result;
	}
}
