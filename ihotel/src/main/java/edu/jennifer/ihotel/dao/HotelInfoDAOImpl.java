package edu.jennifer.ihotel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.jennifer.ihotel.model.HotelInfo;

public class HotelInfoDAOImpl implements HotelInfoDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public HotelInfoDAOImpl(DataSource datasource){
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	public HotelInfo getInfo(long rd) {
		try{
			long randomDelay = toMySqlSeconds(rd);			
			String query = "SELECT name,address,phone,email,intro,SLEEP(?) FROM hotel_info";
			return jdbcTemplate.query(query, new Object[]{randomDelay},new ResultSetExtractor<HotelInfo>(){
				public HotelInfo extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					if(rs.next()){
						HotelInfo info = new HotelInfo();
						info.setName(rs.getString("name"));
						info.setAddress(rs.getString("address"));
						info.setPhone(rs.getString("phone"));
						info.setEmail(rs.getString("email"));
						info.setIntro(rs.getString("intro"));
						return info;
					}
					return null;
				}
			});
		}catch(Exception ex){
			return null;
		}
	}

	public String getUpdatedPhone(){
		String query = "SELECT phone,SLEEP(?) FROM hotel_info";
		long d = 2500 / 1000;
		jdbcTemplate.query(query, new Object[]{d}, new ResultSetExtractor<String>(){
			public String extractData(ResultSet arg0) throws SQLException,
					DataAccessException {
				while(arg0.next()){}
				return "";
			}
		});
		return "";
	}

	public long toMySqlSeconds(long value) {
		long result = (value / 1000) / 4;
		return result;
	}

}
