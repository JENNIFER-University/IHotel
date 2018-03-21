package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.util.Common;

/**
 * @author khalid
 * @created 03/04/2017.
 */
public class BookingAction extends BaseAction {

    private int roomNo;
    private Room room;

    @Override
    public String execute() throws Exception {
        Room room = getRoomService().findById(getRoomNo(), Common.getRandom(2000,4000));
        setRoom(room);
        return SUCCESS;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
