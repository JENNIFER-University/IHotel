package edu.jennifer.ihotel.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jennifer.ihotel.dao.*;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.Conf;
import edu.jennifer.ihotel.util.ConnectionUtil;
import edu.jennifer.pluginmanager.PluginLoader;

/**
 * Created by khalid on 31/03/2017.
 */
public class BaseAction extends ActionSupport{

    private ConnectionUtil connectionUtil;
    private RoomDAO roomService;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;
    private UserDAO userDAO;

    private String appVersion;

    public PluginLoader pluginLoader;

    public BaseAction(){

        connectionUtil = ConnectionUtil.getInstance();
        setAppVersion(Common.APP_VERSION);
        try{
            System.out.println(Conf.getInstance().getProperty("plugin_dir"));
            pluginLoader = PluginLoader.getInstance(Conf.getInstance().getProperty("plugin_dir"));
            pluginLoader.loadPlugins();
            System.out.println("Loaded " + pluginLoader.listPluginNames().size());
        }catch (Exception ex){
            //handle me later
            System.out.println("aaosdlkasjdlkasjdlkajsldkjaslkdjlasjdlkajslkdjaslkjlaksd");
            ex.printStackTrace();
        }
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

    public UserDAO getUserDAO() {
        userDAO = new UserDAOImpl(this.connectionUtil.getDataSource());
        return userDAO;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }



    public String getAppVersion() {
        return appVersion;
    }
}
