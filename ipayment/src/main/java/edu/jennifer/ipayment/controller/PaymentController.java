package edu.jennifer.ipayment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import edu.jennifer.ipayment.model.DAOFactory;
import edu.jennifer.ipayment.model.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController{


	@RequestMapping("/makePayment")
	public Transaction makePayment(@RequestParam(name = "reservation_id") String reservation_id,
								   @RequestParam(name = "ammount") String ammount,
								   @RequestParam(name = "cardnumber") String cardnumber,
								   @RequestParam(name = "cardholder") String cardholder,
								   @RequestParam(name = "expire") String expire ){
		Transaction transaction = new Transaction();
		transaction.setId(getCurrentTimeStamp());
		transaction.setReservation_id(reservation_id);
		transaction.setAmmount(ammount);
		transaction.setCardNumber(cardnumber);
		transaction.setCardHolder(cardholder);
		transaction.setCardExpire(expire);

		try{
			DAOFactory.getTransactionDAO().save(transaction);
			return transaction;
		}catch (Exception ex){
			return null;
		}

	}


	private static String getCurrentTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
