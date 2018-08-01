package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.model.RoomType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khalid
 * @created 31/03/2017.
 */
public class RoomsAction extends BaseAction {


    private ArrayList<Room> rooms;
    private List<RoomType> roomTypes;

    @Override
    public String execute() throws Exception {
        ArrayList<Room> roomsList = getRoomService().findAll();
        setRooms(roomsList);
        setRoomTypes(getRoomService().findAllRoomTypes());
        if (isSimula()) {
            doLongTx();
        }
        return SUCCESS;
    }


    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRoomTypes(ArrayList<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }
}
