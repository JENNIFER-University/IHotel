package edu.jennifer.ihotel.action;

import edu.jennifer.ihotel.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by khalid on 31/03/2017.
 */
public class WelcomeAction extends BaseAction {

    private ArrayList<Room> rooms;

    @Override
    public String execute() throws Exception {
        setRooms(getRoomService().findFeatured());
        return SUCCESS;
    }

    public String about(){
        return SUCCESS;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
