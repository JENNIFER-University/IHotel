package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

	User getUser(String id) throws SQLException;
	User getProfile(String userName, String id);
	User login(String username, String password);
	ArrayList<User> findAll();
	boolean save(User user);
	boolean userNameExists(String username);
}
