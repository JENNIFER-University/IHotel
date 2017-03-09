package edu.jennifer.ihotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.jennifer.ihotel.dao.HotelInfoDAO;
import edu.jennifer.ihotel.model.HotelInfo;
import edu.jennifer.ihotel.util.Actions;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.HotelExceptions;

@Controller
public class ContactController {

	@Autowired
	private HotelInfoDAO hotelInfoService;
	
	@RequestMapping(value=Actions.Contact_ACTION)
	public ModelAndView landingPage(ModelAndView mv) throws HotelExceptions {
		HotelInfo hInfo = hotelInfoService.getInfo(0);
		
		checkHotelEmail(hInfo.getEmail()+"-"+hInfo.getEmail(),8);
		
		return mv;
	}
	
	private void checkHotelEmail(String email, int depth) throws HotelExceptions{
		if(depth == 0){
			long napTime = Common.getRandom(4000, 9000);
			Common.logTime(napTime);
			if(napTime > 0) 
				throw new HotelExceptions("Unable to get hotel email");			
		}else{
			checkHotelEmail(email, depth -1 );
		}
	}
}
