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


public class UserDAOImpl implements UserDAO{


	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
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

	@Override
	public boolean isUsernameUnique(String username) {
		try{
			String query = "SELECT * FROM users where username = ?";
			User user = jdbcTemplate.query(query, new String[]{username}, new ResultSetExtractor<User>() {
				public User extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if(rs.next()){
						return new User();
					}
					return null;
				}
			});
			return user == null;
		}catch(Exception ex){
			return false;
		}
	}

	public User getProfile(String userName, String userId ) {
		if (ProblemPool.getInstance().makeProblem(ProblemPool.SLOW_LOGIN)) {
			if (userName.toLowerCase().equals("khalid")) {
				return loadProfile(userName);
			}
		}
		return null;
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


	public User loadProfile(String userName) {
		long startTime = System.currentTimeMillis();
		for(int i =0; ; i++) {
			long now = System.currentTimeMillis();
			if (now - startTime >= Common.getRandom(25000, 30000)) break;
		}

		return new User();
	}
}

