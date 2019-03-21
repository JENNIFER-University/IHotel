package edu.jennifer.hotel.action;

import edu.jennifer.hotel.exceptions.ParseException;
import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.model.RoomType;
import edu.jennifer.hotel.problem.ProblemPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khalid
 * @Created 3/20/18 5:04 PM.
 */
public class RoomsFilterAction extends BaseAction {

    private String type;

    private ArrayList<Room> rooms;
    private List<RoomType> roomTypes;

    public String execute() {
        init();
        RoomType roomTypeResult = getRoomService().findTypeByName(parseType());

        setRooms(getRoomService().findByType(roomTypeResult.getId()));

        setRoomTypes(getRoomService().findAllRoomTypes());

        if(getType().startsWith("3") && ProblemPool.getInstance().makeProblem(ProblemPool.METHOD_EXCEPTION)) {
            throw new ParseException("Unable to parse room type");
        }

        return SUCCESS;
    }


    public String parseType() {
        return type.replace("-", " ");
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
