package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.problem.ProblemPool;
import edu.jennifer.hotel.util.Common;
import edu.jennifer.hotel.util.RoomAvailablityCheck;

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

        if (ProblemPool.getInstance().makeProblem(ProblemPool.DEAD_LOCK)) {
            Object obj1 = new Object();
            Object obj2 = new Object();
            Object obj3 = new Object();

            Thread thread1 = new Thread(new RoomAvailablityCheck(obj1, obj2), "RoomAvailablityCheck-1");
            Thread thread2 = new Thread(new RoomAvailablityCheck(obj2, obj3), "RoomAvailablityCheck-2");
            Thread thread3 = new Thread(new RoomAvailablityCheck(obj3, obj1), "RoomAvailablityCheck-3");

            thread1.start();
            try { Thread.sleep(3000); } catch (InterruptedException ex){}
            thread2.start();
            try { Thread.sleep(3000); } catch (InterruptedException ex){}
            thread3.start();


        }
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
