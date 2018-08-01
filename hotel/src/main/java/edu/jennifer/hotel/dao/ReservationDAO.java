package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.Reservation;

public interface ReservationDAO {

	int BOOKED 	= 1;
	int PAYED 	= 2;
	
	String reserveRoom(Reservation r);

	boolean confirmReservation(String id);

	Reservation findByReservationId(String reservationId);
}
