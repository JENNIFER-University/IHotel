package edu.jennifer.stress.model;

import edu.jennifer.common.AppUtil;

/**
 * @author Khalid
 * @Created 3/21/18 3:02 PM.
 */
public class Room {

    private static final String[] TYPES = { "Single-Room", "Double-Room", "King-Size-Bedroom", "Business-Suite", "3-Room-Apartment", "Luxurious-Suite" };

    private int roomId;
    private String roomType;

    public static Room createRoom(){
        Room room = new Room();
        room.setRoomId(AppUtil.getRandom(1, 12));
        room.setRoomType(TYPES[AppUtil.getRandomBounded(TYPES.length)]);
        return room;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
