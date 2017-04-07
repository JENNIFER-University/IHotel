package edu.jennifer.ipayment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jennifer.ipayment.model.DAOFactory;
import edu.jennifer.ipayment.model.Transaction;

@WebServlet(name="PaymentController" ,urlPatterns={"/makePayment"})
public class PaymentController extends BaseController{


	private static final long serialVersionUID = 1L;

	@Override
	public void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		try{
			Transaction data = parse(req);
			DAOFactory.getTransactionDAO().save(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


	private Transaction parse(HttpServletRequest req){
		String reservation_id = req.getParameter("reservation_id");
        String ammount = req.getParameter("ammount");
        String cardnumber = req.getParameter("cardnumber");
        String cardholder = req.getParameter("cardholder");
       	String expire = req.getParameter("expire");
		String id = getCurrentTimeStamp();

		Transaction t = new Transaction();
		t.setId(id);
		t.setAmmount(ammount);
		t.setCardNumber(cardnumber);
		t.setCardHolder(cardholder);
		t.setCardExpire(expire);
		t.setReservation_id(reservation_id);
		return t;
	}

	public static String getCurrentTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
