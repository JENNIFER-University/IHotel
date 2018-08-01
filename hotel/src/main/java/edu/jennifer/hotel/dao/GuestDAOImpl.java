package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Guest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDAOImpl extends BaseDao implements GuestDAO {

	public GuestDAOImpl(DataSource dataSource){
		super(dataSource);
	}

	/**
	 * Save the guest information
	 * @param guest
	 * @return
	 */
	public int saveGuestInformation(Guest guest) {
		try{
			String query = "INSERT INTO guests (title,firstname,lastname,address,phone,email) VALUES (?,?,?,?,?,?)";
			int saved = jdbcTemplate.update(query,guest.getTitle(),guest.getFirstname(),guest.getLastname(),guest.getAddress(),guest.getPhone(),guest.getEmail());
			if(saved > 0 ){
			    return jdbcTemplate.queryForObject("select max(id) from guests", Integer.class);
			}

			return -1;
		}catch(Exception ex){
			return -1;
		}

	}

	/**
	 * Find Guest By Email
	 * @param email email address
	 * @return
	 */
	public Guest findByEmail(String email) {
		try{
			String query = "SELECT id,title,firstname,lastname,address,phone,email,SLEEP(?) from guests where email = ?";
			Guest g = jdbcTemplate.query(query, new Object[]{mySqlDelay(),email},new ResultSetExtractor<Guest>(){
				public Guest extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
					if(rs.next()){
						Guest g = new Guest();
						g.setId(rs.getInt("id"));
						g.setTitle(rs.getString("title"));
						g.setFirstname(rs.getString("firstname"));
						g.setLastname(rs.getString("lastname"));
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
}
