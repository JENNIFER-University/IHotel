package edu.jennifer.hotel.dao;

import edu.jennifer.common.AppUtil;
import edu.jennifer.hotel.model.User;
import edu.jennifer.hotel.problem.ProblemPool;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAOImpl extends BaseDao implements UserDAO{

	public UserDAOImpl(DataSource dataSource) {
		super(dataSource);
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
			return jdbcTemplate.query(query, new String[]{username, plainToMD5(password)}, rs -> {
        if(rs.next()){
            User u = new User();
            u.setId(rs.getString("id"));
            u.setPassword("");
            u.setUsername(rs.getString("username"));
            u.setRealName(rs.getString("realname"));
            return u;
        }
                return null;
            });

		}catch(Exception ex){
			return null;
		}
	}

	/**
	 * Check if username exists
	 * @param username
	 * @return
	 */
	public boolean isUsernameUnique(String username) {
		try{
			String query = "SELECT * FROM users where username = ?";
			User user = jdbcTemplate.query(query, new String[]{username}, rs -> {
                if(rs.next()){
                    return new User();
                }
                return null;
            });
			return user == null;
		}catch(Exception ex){
			return false;
		}
	}

	/**
	 * Load profile (For Simula Slow Login)
	 * @param userName
	 * @param userId
	 * @return
	 */
	public User getProfile(String userName, String userId ) {
		if (ProblemPool.getInstance().makeProblem(ProblemPool.SLOW_LOGIN)) {
			if (userName.toLowerCase().equals("khalid")) {
				return loadProfile(userName);
			}
		}
		return null;
	}

	/**
	 * Save user
	 * @param user
	 * @return
	 */
	public boolean save(User user){
		try{
			String userId = String.format("%d-%s", AppUtil.getRandom(100,10000), AppUtil.getCurrentTimeStamp() );
			if (ProblemPool.getInstance().makeProblem(ProblemPool.SQL_EXCEPTION) ) {
				userId = "" +1;
				user.setUsername(null);
			}
			String query = "INSERT INTO users values (?,?,?,?)";
			int v = jdbcTemplate.update(query, userId, user.getUsername(), plainToMD5(user.getPassword()), user.getRealName());
			return v > 0;
		}catch(Exception ex){
			return false;
		}
	}

	/**
	 * Slow Login (Loading profile)
	 * @param userName
	 * @return
	 */
	private User loadProfile(String userName) {
		long startTime = System.currentTimeMillis();
		for(int i =0; ; i++) {
			long now = System.currentTimeMillis();
			if (now - startTime >= AppUtil.getRandom(25000, 30000)) break;
		}

		return new User();
	}

	/**
	 * Plain Text to MD5
	 * @param text
	 * @return
	 */
	private String plainToMD5(String text){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes(), 0, text.length());
			return new BigInteger(1,md.digest()).toString(16);
		}catch(NoSuchAlgorithmException ex){
			return text;
		}
	}
}

