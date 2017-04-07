package edu.jennifer.ihotel.action;

import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.util.Common;

import java.util.ArrayList;

/**
 * Created by khalid on 31/03/2017.
 */
public class RoomsAction extends BaseAction {

    private int id;
    private Room room;
    private ArrayList<Room> rooms;

    /**
     * Get All rooms
     * @return
     */
    public String list(){
        ArrayList<Room> roomsList = getRoomService().findAll(Common.getRandom(2000, 4000));
        setRooms(roomsList);
        return SUCCESS;

    }

    /**
     * View a Single room
     */
    public String view(){
        Room room = getRoomService().findById(getId(), Common.getRandom(2000,4000));
        setRoom(room);
        return SUCCESS;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
