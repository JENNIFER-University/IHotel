package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.model.Transaction;
import edu.jennifer.ipayment.service.TransactionService;
import edu.jennifer.ipayment.util.invalidReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentDetailsController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping("/payment_detail")
	public @ResponseBody  Transaction getPaymentDetails(@RequestParam(value = "resverationId") String resverationId){

		System.out.println(resverationId);
		if (resverationId == null || resverationId.startsWith("ex")) {
			throw new invalidReservationException("Invalid Reservation Card Number");
		}
		return transactionService.findByReservationId(resverationId);
	}
}
