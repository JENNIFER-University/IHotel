package edu.jennifer.ihotel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.jennifer.ihotel.model.Guest;

public class GuestDAOImpl implements GuestDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public GuestDAOImpl(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int saveGuestInformationh(Guest guest) {
		try{
			String query = "INSERT INTO guests (title,forenames,surename,dob,address,phone,email) VALUES (?,?,?,?,?,?,?)";
			int saved = jdbcTemplate.update(query,guest.getTitle(),guest.getForName(),guest.getSureName(),guest.getDateOfBirth(),guest.getAddress(),guest.getPhone(),guest.getEmail());
			if(saved > 0 ){
				return jdbcTemplate.queryForInt("select max(id) from guests");
			}
			return -1;
		}catch(Exception ex){
			return -1;
		}
	}

	public Guest findByEmail(String email, long rd) {
		try{
			long randomDelay = toMySqlSeconds(rd);
			String query = "SELECT id,title,forenames,surename,dob,address,phone,email,SLEEP(?) from guests where email = ?";
			Guest g = jdbcTemplate.query(query, new Object[]{randomDelay,email},new ResultSetExtractor<Guest>(){
				public Guest extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if(rs.next()){
						Guest g = new Guest();
						g.setId(rs.getInt("id"));
						g.setTitle(rs.getString("title"));
						g.setForName(rs.getString("forenames"));
						g.setSureName(rs.getString("surename"));
						g.setDateOfBirth(rs.getString("dob"));
						g.setAddress(rs.getString("address"));
						g.setPhone(rs.getString("phone"));
						g.setEmail(rs.getString("email"));
						return g;
					}
					return null;
				}
			});
			
			return g;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Guest> findAll() {
		try{
			String query = "SELECT id,title,forenames,surename,dob,address,phone,email from guests";
			ArrayList<Guest> result = jdbcTemplate.query(query, new ResultSetExtractor<ArrayList<Guest>>(){
				public ArrayList<Guest> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					ArrayList<Guest> guest = new ArrayList<Guest>();
					while(rs.next()){
						Guest g = new Guest();
						g.setId(rs.getInt("id"));
						g.setTitle(rs.getString("title"));
						g.setForName(rs.getString("forenames"));
						g.setSureName(rs.getString("surename"));
						g.setDateOfBirth(rs.getString("dob"));
						g.setAddress(rs.getString("address"));
						g.setPhone(rs.getString("phone"));
						g.setEmail(rs.getString("email"));
						guest.add(g);
					}
					return guest;
				}
			});
			return result;
		}catch(Exception ex){
			return null;
		}
	}

	public int getBookingNumber(Guest g) {
		try{
			String query = "SELECT COUNT(id) as bookings FROM reservations WHERE guest_id = ?";
			int guestBookingCount = jdbcTemplate.query(query, new Object[]{g.getId()},new ResultSetExtractor<Integer>(){
				public Integer extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if(rs.next())
						return rs.getInt("bookings");
					else
						return 0;
				}
				
			});
			return guestBookingCount;
		}catch(Exception ex){
			return -1;
		}
	}

	public long toMySqlSeconds(long value) {
		long result = (value / 1000) / 4;
		return result;
	}
}
