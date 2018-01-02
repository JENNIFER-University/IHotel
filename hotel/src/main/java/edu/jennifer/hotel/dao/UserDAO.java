package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.User;

public interface UserDAO {

	User getProfile(String userName, String id);

	User login(String username, String password);

	boolean save(User user);

}
