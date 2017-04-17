package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.Guest;

import java.util.ArrayList;

public interface GuestDAO {

	public int saveGuestInformationh(Guest guest);
	
	public Guest findByEmail(String email, long randomDelay);

	public ArrayList<Guest> findAll();
	
	public int getBookingNumber(Guest g);
	
	public long toMySqlSeconds(long value);
	
}
