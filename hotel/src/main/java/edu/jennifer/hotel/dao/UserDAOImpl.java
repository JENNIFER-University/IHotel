package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.User;
import edu.jennifer.hotel.problem.ProblemPool;
import edu.jennifer.hotel.util.Common;
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
	public User login(String username, String password) {
		try{
			String query = "SELECT * FROM users where username = ? AND password = ?";
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


//			if (user != null && ProblemPool.getInstance().makeProblem(ProblemPool.SLOW_LOGIN) && username.toLowerCase().equals("khalid")) {
//				loadProfile();
//			}

			return user;
		}catch(Exception ex){
			return null;
		}
	}

	public User getProfile(String userName,String userId ) {
		if (ProblemPool.getInstance().makeProblem(ProblemPool.SLOW_LOGIN)) {
			if (userName.toLowerCase().equals("khalid")) {
				return loadProfile(userName);
			}
		}
		return null;
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
			String userId = String.format("%d-%s", Common.getRandom(100,10000), Common.getCurrentTimeStamp() );
			if (ProblemPool.getInstance().makeProblem(ProblemPool.SQL_EXCEPTION) ) {
				userId = "" +1;
				user.setUsername(null);
			}
			String query = "INSERT INTO users values (?,?,?,?)";
			int v = jdbcTemplate.update(query, userId, user.getUsername(), Common.plainToMD5(user.getPassword()), user.getRealName());
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

//	private void loadProfile(){
//		long startTime = System.currentTimeMillis();
//		for(int i =0; ; i++) {
//			long now = System.currentTimeMillis();
//			if (now - startTime >= Common.getRandom(25000, 30000)) break;
//		}
//	}

	public User loadProfile(String userName) {
		long startTime = System.currentTimeMillis();
		for(int i =0; ; i++) {
			long now = System.currentTimeMillis();
			if (now - startTime >= Common.getRandom(25000, 30000)) break;
		}

		return new User();
	}
}

