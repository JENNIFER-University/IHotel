package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Reservation;

public interface ReservationDAO {

	int BOOKED 	= 1;
	int PAYED 	= 2;
	int UNKIWN  = 0;
	
	String reservRoom(Reservation r);

	boolean confirmReservation(String id);

	Reservation findByReservationId(String reservationId, long randomDelay);

	long toMySqlSeconds(long value);
}
