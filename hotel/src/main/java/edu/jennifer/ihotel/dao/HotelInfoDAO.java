package edu.jennifer.ihotel.dao;

import edu.jennifer.ihotel.model.HotelInfo;

public interface HotelInfoDAO {

	public HotelInfo getInfo(long sleepTime);
	
	public long toMySqlSeconds(long value);
	
	public String getUpdatedPhone();
	
}
