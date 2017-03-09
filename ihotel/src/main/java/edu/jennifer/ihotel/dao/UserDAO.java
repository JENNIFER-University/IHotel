package edu.jennifer.ihotel.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.jennifer.ihotel.model.User;

public interface UserDAO {

	public User getUser(String id) throws SQLException;
	public String login(String username, String password);
	public ArrayList<User> findAll();
	public boolean save(User user);
	public boolean userNameExists(String username);
}
