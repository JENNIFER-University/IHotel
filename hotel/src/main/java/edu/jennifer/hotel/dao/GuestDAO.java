package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Guest;

public interface GuestDAO {

	int saveGuestInformationh(Guest guest);
	
	Guest findByEmail(String email, long randomDelay);

	long toMySqlSeconds(long value);
	
}
