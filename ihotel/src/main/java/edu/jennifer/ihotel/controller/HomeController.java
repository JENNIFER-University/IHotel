package edu.jennifer.ihotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.jennifer.ihotel.dao.HotelInfoDAO;
import edu.jennifer.ihotel.model.HotelInfo;
import edu.jennifer.ihotel.model.Payment;
import edu.jennifer.ihotel.util.Actions;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.Flash;
import edu.jennifer.ihotel.util.LoadUtil;
import edu.jennifer.ihotel.util.PaymentHelper;
import edu.jennifer.ihotel.util.SessionKeys;

@Controller
public class HomeController extends BaseController{

	private final String LOCK 		= "1";
	private final String RELESAE 	= "2";
	
	@Autowired
	private HotelInfoDAO hotelInfoService;
	

	@RequestMapping(value=Actions.HOME_ACTION)
	public ModelAndView landingPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String hang = request.getParameter("hang");
		String test = request.getParameter("test");
		HotelInfo hInfo = null;
		if(test != null){
			Payment p = new Payment("123", "123", "12", "123", "Test", "123", "123");
			int result   = PaymentHelper.makePayment(p);
			if(result == PaymentHelper.REQUEST_FAILED){
				request.getSession().setAttribute(SessionKeys.FLASH, new Flash("Testing Ipayment Failed, please check your configuration", Flash.ERROR));
			}else{
				request.getSession().setAttribute(SessionKeys.FLASH, new Flash("iPayment Test Was Success", Flash.SUCCESS));
			}
		}
		if(hang == null){
			long randomDelay = initalize();
			hInfo = hotelInfoService.getInfo(randomDelay);
		}else{
			hInfo = hotelInfoService.getInfo(0);
			long delay;
			try{
				delay = Long.parseLong(hang);
			}catch(NumberFormatException ex){
				delay = 9000;
			}
			

			Common.logTime(delay);
		}

		mv.addObject("hotelInfo", hInfo);
	    mv.setViewName("home/index");
	    return mv;
	}
	
	@RequestMapping(value=Actions.INTRO_ACTION)
	public ModelAndView hotelIntro(ModelAndView mv){
		getHotelPhone(10);
		mv.setViewName("home/intro");
	    return mv;
	}
	
	private String getHotelPhone(int lvl){
		if(lvl == 0){
			hotelInfoService.getUpdatedPhone();
			Common.logTime(1100);
			return "+82-1234-5678";
		}else{
			Common.logTime(10);
			getHotelPhone(lvl -1);	
			return "";
		}
		
	}
	
//	
//	@RequestMapping(value="/qu.do")
//	public ModelAndView fileWritter(HttpServletRequest request){
//		String cmd = request.getParameter("cmd");
//		if(cmd == null || cmd.length() == 0)
//			cmd = "";
//		
//		long now = System.currentTimeMillis();
//		
//		if(cmd.equals(LOCK)){
//			PlcHelper.getIntance().writeToTheFile();			
//		}else if(cmd.equals(RELESAE)){
//			File releaseSignal = new File(PlcHelper.UNLOCK_FILE_NAME);
//			try{releaseSignal.createNewFile();}catch(Exception ex){System.out.println("Ops, failed to create release signal. now stuck for ever");}
//		}else{
//			System.out.println("UNKOWN CMD");
//			throw new RuntimeException("Unkown Command");
//		}
//		long differ = now - System.currentTimeMillis();
//		ModelAndView mv = new ModelAndView("home/qu");
//		mv.addObject("timetaken", (differ/1000));
//		return mv;
//	}
	
	@RequestMapping(value="/file.do")
	public ModelAndView fileReader(HttpServletRequest request){
		long now = System.currentTimeMillis();
//		PlcHelper.getIntance().readTheFile();
		long differ = now - System.currentTimeMillis();
		ModelAndView mv = new ModelAndView("home/file");
		mv.addObject("timetaken", (differ/1000));
		return mv;
	}
	
	
}
