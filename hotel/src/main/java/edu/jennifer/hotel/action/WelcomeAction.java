package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Room;

import java.util.ArrayList;

/**
 * @author khalid
 * @created 31/03/2017.
 */
public class WelcomeAction extends BaseAction {

    private ArrayList<Room> rooms;

    @Override
    public String execute() throws Exception {
        init();
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
