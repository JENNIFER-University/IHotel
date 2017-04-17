package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

	public User getUser(String id) throws SQLException;
	public String login(String username, String password);
	public ArrayList<User> findAll();
	public boolean save(User user);
	public boolean userNameExists(String username);
}
