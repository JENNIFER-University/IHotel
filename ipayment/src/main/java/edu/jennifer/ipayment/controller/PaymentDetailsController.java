package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.model.Transaction;
import edu.jennifer.ipayment.service.TransactionService;
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
		return transactionService.findByReservationId(resverationId);
	}
}
