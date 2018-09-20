package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Room;

/**
 * @author khalid
 * @created 03/04/2017.
 */
public class BookingAction extends BaseAction {

    private int roomNo;
    private Room room;

    @Override
    public String execute() throws Exception {
        init();
        Room room = getRoomService().findById(getRoomNo());
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
