package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Guest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDAOImpl implements GuestDAO {

    private JdbcTemplate jdbcTemplate;

	public GuestDAOImpl(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int saveGuestInformationh(Guest guest) {
		try{
			String query = "INSERT INTO guests (title,forenames,surename,dob,address,phone,email) VALUES (?,?,?,?,?,?,?)";
			int saved = jdbcTemplate.update(query,guest.getTitle(),guest.getForName(),guest.getSureName(),guest.getDateOfBirth(),guest.getAddress(),guest.getPhone(),guest.getEmail());
			if(saved > 0 ){
			    return jdbcTemplate.queryForObject("select max(id) from guests", Integer.class);
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


	public long toMySqlSeconds(long value) {
		long result = (value / 1000) / 4;
		return result;
	}
}
