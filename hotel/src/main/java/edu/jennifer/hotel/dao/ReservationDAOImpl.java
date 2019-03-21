package edu.jennifer.hotel.dao;

import edu.jennifer.common.AppUtil;
import edu.jennifer.hotel.model.Reservation;
import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.model.RoomType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReservationDAOImpl extends BaseDao implements ReservationDAO {


	public ReservationDAOImpl(DataSource ds){
		super(ds);
	}


	/**
	 * Reserve a room
	 * @param r
	 * @return
	 */
	public String reserveRoom(Reservation r) {
		try{

			String query = "INSERT INTO reservations (id,guest_id,roomId,checkInDate,checkOutDate,guestsNumber,status) values(?,?,?,?,?,?,?)";
			String rId = r.getId() == null ? AppUtil.getCurrentTimeStamp() : r.getId();
			int saved = jdbcTemplate.update(query,rId,r.getGuest().getId(),r.getRoom().getId(),r.getCheckIn(),r.getCheckOut(),r.getGuestNumbers(),BOOKED);

			if(saved > 0)
				return rId;
			else
				return "-1";
		}catch(Exception ex){
			return "-1";
		}
	}


	/**
	 * Confirm the reservation fo the room after payment
	 * @param id
	 * @return
	 */
	public boolean confirmReservation(String id) {
		try{
			String query = "UPDATE reservations SET status = ? WHERE id = ?";
			int updated = jdbcTemplate.update(query,PAYED,id);
			return updated > 0;
		}catch(Exception ex){
			return false;
		}
	}

	/**
	 * Get a reservation by id
	 * @param reservationId
	 * @return
	 */
	public Reservation findByReservationId(String reservationId){
		try{
			float randomDelay = mySqlDelay();
			String query = "SELECT r.id,r.checkInDate,r.checkOutDate,r.guestsNumber,r.status,"
						+ "rm.number as roomno, rm.floor as roomfloor, rm.price as roomprice, rm.description as description,"
						+ "rt.roomType,rt.roomSize,rt.maxCapacity,SLEEP(?)"
						+ " FROM reservations r"
						+ " LEFT JOIN rooms rm"
						+ " on r.roomId = rm.id"
						+ " LEFT JOIN rooms_type rt"
						+ " on rm.room_type = rt.id"
						+ " WHERE r.id = ?" ;
			return jdbcTemplate.query(query, new Object[]{randomDelay,reservationId}, new ResultSetExtractor<Reservation>(){
				Reservation result = new  Reservation();

				public Reservation extractData(ResultSet rs)
						throws SQLException, DataAccessException {

					while(rs.next()){
						RoomType rt = new RoomType();
						rt.setMaxCapacity(rs.getInt("maxCapacity"));
						rt.setRoomSize(rs.getString("roomSize"));
						rt.setRoomType(rs.getString("roomType"));

						Room room = new Room();
						room.setRoomNumber(rs.getInt("roomno"));
						room.setFloor(rs.getString("roomfloor"));
						room.setPrice(rs.getDouble("roomprice"));
						room.setDescription(rs.getString("description"));
						room.setRoomType(rt);

						result.setId(rs.getString("id"));
						result.setCheckIn(rs.getString("checkInDate"));
						result.setCheckOut(rs.getString("checkOutDate"));
						result.setGuestNumbers(rs.getInt("guestsNumber"));
						result.setStatus(rs.getInt("status"));
						result.setRoom(room);

					}
					return result;
				}
			});

		}catch(Exception ex){
			return null;
		}
	}

}
