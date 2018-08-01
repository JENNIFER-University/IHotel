package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Guest;

public interface GuestDAO {

	int saveGuestInformation(Guest guest);
	
	Guest findByEmail(String email);
	
}
