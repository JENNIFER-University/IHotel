package edu.jennifer.stress.model;

import edu.jennifer.stress.util.AppUtil;

import java.util.Random;

/**
 * @author Khalid
 * @Created 3/21/18 3:02 PM.
 */
public class Room {

    private final String[] TYPES = { "Single-Room", "Double-Room", "King-Size-Bedroom", "Business-Suite", "3-Room-Apartment", "Luxurious-Suite" };

    private int roomId;
    private String roomType;

    public Room(){
        this.roomId = AppUtil.getRandom(1, 12);
        this.roomType = TYPES[new Random().nextInt(TYPES.length)];
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

}
