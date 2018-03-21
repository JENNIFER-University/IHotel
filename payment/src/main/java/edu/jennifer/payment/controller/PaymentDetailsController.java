package edu.jennifer.payment.controller;

import edu.jennifer.payment.model.Transaction;
import edu.jennifer.payment.service.TransactionService;
import edu.jennifer.payment.util.invalidReservationException;
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
		if (resverationId == null || resverationId.startsWith("ex")) {
			throw new invalidReservationException("Invalid Reservation Card Number");
		}
		return transactionService.findByReservationId(resverationId);
	}
}
