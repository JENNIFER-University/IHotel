package edu.jennifer.ihotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.jennifer.ihotel.dao.GuestDAO;
import edu.jennifer.ihotel.dao.ReservationDAO;
import edu.jennifer.ihotel.dao.RoomDAO;
import edu.jennifer.ihotel.model.Guest;
import edu.jennifer.ihotel.model.Payment;
import edu.jennifer.ihotel.model.Reservation;
import edu.jennifer.ihotel.model.Room;
import edu.jennifer.ihotel.util.Actions;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.Flash;
import edu.jennifer.ihotel.util.PaymentHelper;
import edu.jennifer.ihotel.util.RandomString;
import edu.jennifer.ihotel.util.SessionKeys;

@Controller
@RequestMapping(value="/booking")
public class BookingController extends BaseController{

	@Autowired
	RoomDAO roomsService;
	@Autowired
	GuestDAO guestsService;
	@Autowired
	ReservationDAO reservationService;
	
	@RequestMapping(value=Actions.BOOK_ACTION)
	public ModelAndView bookRoom(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("booking/index");
		long randomDelay = initalize();
		int roomId = Integer.parseInt(request.getParameter("roomNo"));
		mv.addObject("room",roomsService.findById(roomId,randomDelay));
		return mv;
		
	}
	
	@RequestMapping(value=Actions.DOBOOK_ACTION)
	public ModelAndView doBook(HttpServletRequest req){
		
		Guest guest = getOrSaveGuest(req);
		
		Reservation reservation = initReservationObject(req, guest);
		String reservationId = reservationService.reservRoom(reservation);
		reservation.setId(reservationId);
		
		Payment paymentInfo = initPaymentInfo(req,guest,reservation);
		paymentInfo.setReservationId(reservationId);
		
		int status = PaymentHelper.makePayment(paymentInfo);
		if(status == 200){
			
			if(reservationService.confirmReservation(reservationId)){
				req.getSession().setAttribute("ihotel.flash", new Flash("Payment Completed Thank you ", "success"));
		        return new ModelAndView("redirect:/booking//booking_complete.do?reservationNumber=" + reservationId);
			}
			
			req.getSession().setAttribute("ihotel.flash", new Flash("Payment authorized successfully but Faild to to complete booking. Please contact our support desk", "danger"));
		    return new ModelAndView("redirect:/booking/failed.do");
		}
		
		req.getSession().setAttribute("ihotel.flash", new Flash("Faild to to complete the payment. Reason [" + PaymentHelper.getErrorReason(status) + "]", "danger"));
		return new ModelAndView("redirect:/booking/failed.do");
	}
	
	@RequestMapping(value=Actions.BOOKING_DONE_ACTION)
	public ModelAndView bookingComplete(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("booking/done");
		initalize();
		String reservationNumber = request.getParameter("reservationNumber");
		mv.addObject("reservationNumber", reservationNumber);
		return mv;
	}

	@RequestMapping(value=Actions.FIND_ACTION)
	public ModelAndView findBooking(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("booking/find");
		initalize();
		return mv;
	}
	
	@RequestMapping(value=Actions.MANAGE_ACTION)
	public ModelAndView manageBooking(HttpServletRequest req){
		long randomDelay = initalize();
		
		String bookingId = req.getParameter("booking");
		String byString = "Booking Result For Booking Reference[ " + bookingId +"]";
		
		Reservation result = reservationService.findByReservationId(bookingId, randomDelay);
		if(result == null){
			req.getSession().setAttribute(SessionKeys.FLASH, new Flash("No Booking was found", Flash.ERROR));
			return new ModelAndView("redirect:/booking/find.do");
		}
		
		Payment p = PaymentHelper.getPaymentDetails(result.getId());
		
		result.setPaymentDetails(p);
		
		ModelAndView mv = new ModelAndView("booking/manage");		
		mv.addObject("bookings",result);
		mv.addObject("byString",byString);
		return mv;
	}
	
	@RequestMapping(value=Actions.BOOKING_CANCEL_ACTION)
	public ModelAndView cancelBooking(ModelAndView mv){
		mv.setViewName("booking/cancel");
		return mv;
	}
	
	@RequestMapping(value=Actions.BOOKING_FAILED_ACTION)
	public ModelAndView bookingFailed(ModelAndView mv){
		mv.setViewName("booking/failed");
		return mv;
	}
	
	private Guest getOrSaveGuest(HttpServletRequest req){
		long randomDelay = initalize();
		String emailAddress = req.getParameter("email");
		if(emailAddress == null || emailAddress.length() <= 0){
			return null;
		}
	
		Guest g = guestsService.findByEmail(emailAddress,randomDelay);
		if(g == null){
			g = new Guest();
			g.setTitle(req.getParameter("title").length() < 0 ? "Mr" : req.getParameter("title"));
			g.setSureName(req.getParameter("surname").length() < 0 ? "Sami" : req.getParameter("surname"));
			g.setForName(req.getParameter("firstname").length() < 0 ? "zhang" : req.getParameter("firstname"));
			g.setDateOfBirth(req.getParameter("dob").length() < 0 ? "January 31st 1986" : req.getParameter("dob")); 
			g.setAddress(req.getParameter("address").length() < 0 ? "123 Street" : req.getParameter("address"));
			g.setPhone(req.getParameter("phone").length() < 0 ? "0123456" : req.getParameter("phone"));
			g.setEmail(req.getParameter("email"));
			int id = guestsService.saveGuestInformationh(g);
			g.setId(id);
			return g;
		}else{		
			return g;
		}
	}

	private Reservation initReservationObject(HttpServletRequest req, Guest guest){
		Reservation reservation = new Reservation();
		Room room = new Room();
		int roomNo = 1;
		try{
			 if ((req.getParameter("roomNo") != null) && (req.getParameter("roomNo").length() > 0)){
				 roomNo = Integer.parseInt(req.getParameter("roomNo"));
				 room.setId(roomNo);
			 }
		}catch(Exception ex){
			room.setId(1);
		}
		
		String checkin = req.getParameter("checkin") == null ? Common.getCurrentDate() : req.getParameter("checkin");
	    String checkout = req.getParameter("checkout") == null ? Common.getCurrentDate() : req.getParameter("checkout");
	    String guestsno = req.getParameter("guestsno") == null ? "2" : req.getParameter("guestsno");
	    
	    int guestsNumber;
	    try{
	    	guestsNumber = Integer.parseInt(guestsno);
	    }catch(NumberFormatException nf){
	    	guestsNumber = 1;
	    }
	    
	    reservation.setCheckIn(checkin);
	    reservation.setCheckOut(checkout);
	    reservation.setGuestNumbers(guestsNumber);
	    reservation.setRoom(room);
	    reservation.setGuest(guest);
		return reservation;
	}
	
	private Payment initPaymentInfo(HttpServletRequest req,Guest guest, Reservation reservation){
		
		Payment payment = null;
		if (!req.getMethod().toUpperCase().equals("POST")){
			String cardNumber = null;
			try{
		        cardNumber = req.getParameter("card");
		      }catch (Exception ex){
		        cardNumber = new RandomString(16).nextString();
		      }
		      payment = generatePaymentInfo(cardNumber);
		}else{
			payment = parsePayment(req, guest);
		}
		
		return payment;
	}
	
	  private Payment generatePaymentInfo(String cardNumber){
	    Payment p = new Payment();
	    p.setReservationId(Common.getCurrentTimeStamp());
	    p.setCardNumber(cardNumber);
	    p.setCcv(new RandomString(3).nextString());
	    p.setExpire(Common.getCurrentDate());
	    p.setAmmount(Double.toString(Common.getRandom(500, 2000)));
	    p.setCardHolder(Common.getRandomName());
	    return p;
	  }
		
	private Payment parsePayment(HttpServletRequest req,Guest guest){
		Payment p = new Payment();
		p.setCardNumber(req.getParameter("cardno") == null ? "12345678" : req.getParameter("cardno"));
		p.setCardHolder(guest.getForName() + " " + guest.getSureName());
		p.setExpire(req.getParameter("expire") == null ? "05/01/2022" : req.getParameter("expire"));
		p.setCcv(req.getParameter("seccode") == null ? "123" : req.getParameter("seccode"));
		if ((req.getParameter("totalAmount") == null) || (req.getParameter("totalAmount").length() <= 0)) {
			p.setAmmount(Double.toString(Common.getRandom(500, 2000)));
		} else {
			p.setAmmount(req.getParameter("totalAmount"));
		}
		return p;
	}
}
