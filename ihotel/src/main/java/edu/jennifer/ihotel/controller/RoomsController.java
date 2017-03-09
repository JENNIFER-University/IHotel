package edu.jennifer.ihotel.controller;

import edu.jennifer.ihotel.dao.RoomDAO;
import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.util.HotelExceptions;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/rooms"})
public class RoomsController
  extends BaseController
{
  @Autowired
  RoomDAO roomsService;
  
  @RequestMapping({"/list.do"})
  public ModelAndView listRooms(ModelAndView mv) throws Throwable
  {
    long randomDelay = initalize();
    ArrayList<Room> roomsList = this.roomsService.findAll(randomDelay);
    mv.addObject("roomsList", roomsList);
    mv.setViewName("rooms/index");
    return mv;
  }
  
  @RequestMapping({"/view.do"})
  public ModelAndView viewRoom(HttpServletRequest request)
  {
    ModelAndView mv = new ModelAndView("rooms/view");
    long randomDelay = initalize();
    int roomId = Integer.parseInt(request.getParameter("id"));
    Room room = this.roomsService.findById(roomId, randomDelay);
    mv.addObject("room", room);
    return mv;
  }
  
  @RequestMapping({"/check.do"})
  public String checkRoom(HttpServletRequest request) throws NumberFormatException, HotelExceptions{
	  String result = roomsService.checkRoom(Integer.parseInt(request.getParameter("id")));
	  return result;
  }
}
