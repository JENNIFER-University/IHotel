package edu.jennifer.hotel.dao;

import edu.jennifer.hotel.model.HotelInfo;

public interface HotelInfoDAO {

	public HotelInfo getInfo(long sleepTime);
	
	public long toMySqlSeconds(long value);
	
	public String getUpdatedPhone();
	
}
