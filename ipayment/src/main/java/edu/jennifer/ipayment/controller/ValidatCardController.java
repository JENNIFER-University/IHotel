package edu.jennifer.ipayment.controller;

import com.google.gson.JsonObject;
import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="ValidatCardController" ,urlPatterns={"/validateCard/*"})
public class ValidatCardController extends BaseController{

	private static final long serialVersionUID = 1L;


	@Override
	public void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String pathInfo = req.getPathInfo();
	    String[] parts = pathInfo.split("/");
	    String cardNumber = parts[1];
		boolean useICheck = Conf.getInstance().icheckEnabled();
		System.out.println("ICHECK ENABLED RESULT : " + useICheck);
		String result = validateCard(cardNumber,useICheck);
	    resp.setContentType("application/json");
	    resp.getWriter().print(result);

	}

	private String validateCard(String cardNumber,boolean useICheck){
		JsonObject result = new JsonObject();
		if(useICheck){
			String IP   =  Conf.getInstance().getICheckIP();
			String port =  Conf.getInstance().getICheckPort();
			Validator validator = new Validator();
			validator.initialize(IP,port);
			if(validator.checkCard(cardNumber)){
				result.addProperty("error",0);

			}else{
				result.addProperty("error",1);
			}

			return result.toString();

		}else{
			int levels = cardNumber.length();
			if(levels < 5){
				levels = 10;
			}
			checkDigists(levels);

			if(cardNumber.startsWith("500"))
				result.addProperty("error",1);

			result.addProperty("error",0);

			return result.toString();
		}

	}


	private void checkDigists(int levels){
		if(levels == 0){
			return;
		}

		try{Thread.sleep(150);}catch(Exception ex){};
		checkDigists(levels-1);
	}

}
