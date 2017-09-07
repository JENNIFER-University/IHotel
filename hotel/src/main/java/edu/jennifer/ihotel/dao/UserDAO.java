package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

	User getUser(String id) throws SQLException;
	String login(String username, String password, String profile);
	ArrayList<User> findAll();
	boolean save(User user);
	boolean userNameExists(String username);
}
