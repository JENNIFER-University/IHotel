package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.User;
import edu.jennifer.ihotel.util.Common;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO{

	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Get User by ID
	 * @param id the user id
	 * @return User object
	 * @throws SQLException
	 */
	public User getUser(String id) throws SQLException{

			String query = "SELECT USERNAME,REALNAME,EMAIL FROM USR WHERE ID = ?";
			System.out.println("Query " + query);
			User user = jdbcTemplate.query(query, new String[]{id}, new ResultSetExtractor<User>(){
				public User extractData(ResultSet rs) throws SQLException,DataAccessException {
					if(rs.next()){
						User u = new User();
						u.setId(rs.getString("id"));
						u.setUsername(rs.getString("username"));
						u.setRealName(rs.getString("realname"));
						return u;
					}
					return null;
				}
			});
			return user;

	}

	/**
	 * DB Auth ^^
	 * @param username the Username
	 * @param password the password
	 * @return User information as Json
	 */
	public String login(String username, String password) {
		try{
			String query = "SELECT * FROM users where USERNAME = ? AND PASSWORD = ?";
			User user = jdbcTemplate.query(query, new String[]{username,Common.plainToMD5(password)}, new ResultSetExtractor<User>() {
				public User extractData(ResultSet rs) throws SQLException,
						DataAccessException {
			if(rs.next()){
				User u = new User();
				u.setId(rs.getString("id"));
				u.setPassword("");
				u.setUsername(rs.getString("username"));
				u.setRealName(rs.getString("realname"));
				return u;
			}
					return null;
				}
			});

			return user.toJson();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<User> findAll() {
		try{
			String query = "SELECT id,username,realname FROM users";
			ArrayList<User> users = jdbcTemplate.query(query, new ResultSetExtractor<ArrayList<User>>(){
				public ArrayList<User> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					ArrayList<User> usrs = new ArrayList<User>();
					while(rs.next()){
						User u = new User();
						u.setId(rs.getString("id"));
						u.setUsername(rs.getString("username"));
						u.setRealName(rs.getString("realname"));
						usrs.add(u);
					}
					return usrs;
				}
			});
			return users;
		}catch(Exception sql){
			return null;
		}
	}

	public boolean save(User user){
		try{
			String query = "INSERT INTO users values (?,?,?,?)";
			int v = jdbcTemplate.update(query,Common.getCurrentTimeStamp(), user.getUsername(), Common.plainToMD5(user.getPassword()), user.getRealName());
			return v > 0;
		}catch(Exception ex){
			return false;
		}
	}

	public boolean userNameExists(String username) {
		String query = "SELECT * FROM users WHERE username = ? ";
		try{
			boolean exists = jdbcTemplate.query(query, new Object[]{username}, new ResultSetExtractor<Boolean >(){
				public Boolean extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					return rs.next();
				}
			});
			return exists;
		}catch(Exception ex){
			return false;
		}
	}
}
