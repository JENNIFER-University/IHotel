package edu.jennifer.hotel.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jennifer.hotel.dao.*;
import edu.jennifer.hotel.util.Common;
import edu.jennifer.hotel.util.ConnectionUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by khalid on 31/03/2017.
 */
public class BaseAction extends ActionSupport{

    private ConnectionUtil connectionUtil;
    private RoomDAO roomService;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;
    private UserDAO userDAO;

    public BaseAction(){
        connectionUtil = ConnectionUtil.getInstance();
    }

    @Deprecated
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

    public UserDAO getUserDAO() {
        userDAO = new UserDAOImpl(this.connectionUtil.getDataSource());
        return userDAO;
    }

    public boolean isLogged() {
        try {
            String currentUser = ServletActionContext.getRequest().getSession().getAttribute("currentUser").toString();
            return currentUser != null;
        }catch (NullPointerException ne) {
            return false;
        }
    }

    public String getCurrentUser() {
        if (isLogged()) {
            return ServletActionContext.getRequest().getSession().getAttribute("currentUser").toString();
        }

        return null;
    }

}
