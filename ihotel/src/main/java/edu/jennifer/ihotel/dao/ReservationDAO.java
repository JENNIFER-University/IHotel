package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.Guest;
import edu.jennifer.ihotel.model.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO {

	public static final int BOOKED 	= 1;
	public static final int PAYED 	= 2;
	public static final int UNKIWN  = 0;
	
	public String reservRoom(Reservation r);
	
	//For Elproblemo
	public void insert(Reservation r)throws SQLException;
	
	public boolean confirmReservation(String id);
	
	public ArrayList<Reservation> findByCustomerId(final Guest g);
	
	public Reservation findByReservationId(String reservationId, long randomDelay);
	
	public Reservation getReservation();
	
	public long toMySqlSeconds(long value);
}
