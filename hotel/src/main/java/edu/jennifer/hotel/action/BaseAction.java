package edu.jennifer.hotel.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jennifer.common.AppUtil;
import edu.jennifer.hotel.dao.*;
import edu.jennifer.hotel.util.ConnectionUtil;
import org.apache.struts2.ServletActionContext;


/**
 * @author Khalid
 * @created 31/03/2017.
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

    //----------------------- SIMULA ONLY -----------------------//
    private boolean simula = false;
    private final int LOW  = 100;
    private final int HIGH = 1000 * 10;
    private final int LONG_TX_VALUE  = 100;
    private static long txCount = 0;

    public boolean isSimula() {
        return simula;
    }

    public void setSimula(boolean simula) {
        this.simula = simula;
    }

    public void doLongTx() {
        long time = AppUtil.getRandom(LOW, HIGH);
        if (txCount++ > LONG_TX_VALUE) {
            txCount = 0;
            time = AppUtil.getRandom(20000,30000);

        }
        long startTime = System.currentTimeMillis();
        for(int i =0; ; i++) {
            long now = System.currentTimeMillis();
            if (now - startTime >= time) break;
        }
    }

}
