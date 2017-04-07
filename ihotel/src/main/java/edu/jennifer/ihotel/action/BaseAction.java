package edu.jennifer.ihotel.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jennifer.ihotel.dao.*;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.ConnectionUtil;

/**
 * Created by khalid on 31/03/2017.
 */
public class BaseAction extends ActionSupport{

    private ConnectionUtil connectionUtil;
    private RoomDAO roomService;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;

    private String appVersion;

    public BaseAction(){

        connectionUtil = ConnectionUtil.getInstance();
        setAppVersion(Common.APP_VERSION);
    }

    public long initalize(){
		long randomDelay = Common.getRandom(1000, 8500);
		boolean  test = Common.processLong(100, 500, 20);
		if(!test)
			Common.getToken(randomDelay/2);
        return randomDelay;
    }

    public RoomDAO getRoomService() {
        roomService = new RoomDAOImpl(this.connectionUtil.getDataSource());
        return roomService;
    }

    public GuestDAO getGuestDAO() {
        guestDAO = new GuestDAOImpl(this.connectionUtil.getDataSource());
        return guestDAO;
    }

    public ReservationDAO getReservationDAO() {
        reservationDAO = new ReservationDAOImpl(this.connectionUtil.getDataSource());
        return reservationDAO;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }



    public String getAppVersion() {
        return appVersion;
    }
}
