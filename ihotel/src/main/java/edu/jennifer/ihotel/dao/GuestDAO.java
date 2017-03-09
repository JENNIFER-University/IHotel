package edu.jennifer.ihotel.dao;

import java.util.ArrayList;

import edu.jennifer.ihotel.model.Guest;

public interface GuestDAO {

	public int saveGuestInformationh(Guest guest);
	
	public Guest findByEmail(String email, long randomDelay);

	public ArrayList<Guest> findAll();
	
	public int getBookingNumber(Guest g);
	
	public long toMySqlSeconds(long value);
	
}
