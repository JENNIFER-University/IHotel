package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.Guest;
import edu.jennifer.ihotel.model.Reservation;
import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.model.RoomType;
import edu.jennifer.ihotel.util.Common;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReservationDAOImpl implements ReservationDAO {

	private JdbcTemplate jdbTempalte;

	public ReservationDAOImpl(DataSource ds){
		jdbTempalte = new JdbcTemplate(ds);
	}


	public String reservRoom(Reservation r) {
		try{

			String query = "INSERT INTO reservations (id,guest_id,roomId,checkInDate,checkOutDate,guestsNumber,status) values(?,?,?,?,?,?,?)";
			String rId = r.getId() == null ? Common.getCurrentTimeStamp() : r.getId();
			int saved = jdbTempalte.update(query,rId,r.getGuest().getId(),r.getRoom().getId(),r.getCheckIn(),r.getCheckOut(),r.getGuestNumbers(),BOOKED);

			if(saved > 0)
				return rId;
			else
				return "-1";
		}catch(Exception ex){
			return "-1";
		}
	}


	public boolean confirmReservation(String id) {
		try{
			String query = "UPDATE reservations SET status = ? WHERE id = ?";
			int updated = jdbTempalte.update(query,PAYED,id);
			return updated > 0 ? true: false;
		}catch(Exception ex){
			return false;
		}
	}


	public ArrayList<Reservation> findByCustomerId(final Guest g){
		try{
			String query = "SELECT r.id,r.checkInDate,r.checkOutDate,r.guestsNumber,r.status,"
						+ "rm.number as roomno, rm.floor as roomfloor, rm.price as roomprice, rm.description as description,"
						+ "rt.roomType,rt.roomSize,rt.maxCapacity"
						+ " FROM reservations r"
						+ " LEFT JOIN rooms rm"
						+ " on r.roomId = rm.id"
						+ " LEFT JOIN rooms_type rt"
						+ " on rm.room_type = rt.id"
						+ " WHERE r.guest_id = ?";
			return jdbTempalte.query(query, new Object[]{g.getId()}, new ResultSetExtractor<ArrayList<Reservation>>(){
				ArrayList<Reservation> result = new  ArrayList<Reservation>();

				public ArrayList<Reservation> extractData(ResultSet rs)
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

						Reservation r = new Reservation();
						r.setId(rs.getString("id"));
						r.setCheckIn(rs.getString("checkInDate"));
						r.setCheckOut(rs.getString("checkOutDate"));
						r.setGuestNumbers(rs.getInt("guestsNumber"));
						r.setStatus(rs.getInt("status"));
						r.setRoom(room);

						result.add(r);
					}
					return result;
				}
			});
		}catch(Exception ex){
			return null;
		}
	}


	public Reservation findByReservationId(String reservationId, long rd){
		try{
			long randomDelay = toMySqlSeconds(rd);
			String query = "SELECT r.id,r.checkInDate,r.checkOutDate,r.guestsNumber,r.status,"
						+ "rm.number as roomno, rm.floor as roomfloor, rm.price as roomprice, rm.description as description,"
						+ "rt.roomType,rt.roomSize,rt.maxCapacity,SLEEP(?)"
						+ " FROM reservations r"
						+ " LEFT JOIN rooms rm"
						+ " on r.roomId = rm.id"
						+ " LEFT JOIN rooms_type rt"
						+ " on rm.room_type = rt.id"
						+ " WHERE r.id = ?" ;
			return jdbTempalte.query(query, new Object[]{randomDelay,reservationId}, new ResultSetExtractor<Reservation>(){
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


	public Reservation getReservation() {
		try{
			String query = "SELECT r.id,r.checkInDate,r.checkOutDate,r.guestsNumber,r.status,"
						+ "rm.number as roomno, rm.floor as roomfloor, rm.price as roomprice, rm.description as description,"
						+ "rt.roomType,rt.roomSize,rt.maxCapacity"
						+ " FROM reservations r"
						+ " LEFT JOIN rooms rm"
						+ " on r.roomId = rm.id"
						+ " LEFT JOIN rooms_type rt"
						+ " on rm.room_type = rt.id"
						+ " LIMIT 1";
			return jdbTempalte.query(query, new ResultSetExtractor<Reservation>(){
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


	public void insert(Reservation r) throws SQLException {
		String query = "INSERT INTO reservations (id,guest_id,roomId,checkInDate,checkOutDate,guestsNumber,status) values(?,?,?,?,?,?,?)";
		int saved = jdbTempalte.update(query,r.getId(),1,r.getRoom().getRoomNumber(),r.getCheckIn(),r.getCheckOut(),r.getGuestNumbers(),BOOKED);

	}


	public long toMySqlSeconds(long value) {
		long result = (value / 1000) / 4;
		return result;
	}

}
