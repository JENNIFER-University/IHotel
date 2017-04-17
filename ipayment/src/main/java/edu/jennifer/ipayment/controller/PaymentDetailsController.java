package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.model.DAOFactory;
import edu.jennifer.ipayment.model.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentDetailsController {


	@RequestMapping("/payment_detail")
	public @ResponseBody  Transaction getPaymentDetails(@RequestParam(value = "resverationId") String resverationId){

		Transaction result = DAOFactory.getTransactionDAO().findByReservationId(resverationId);
		if(result == null) {
			result = new Transaction();
			result.setId("-1");
		}
		return  result;
	}
}
