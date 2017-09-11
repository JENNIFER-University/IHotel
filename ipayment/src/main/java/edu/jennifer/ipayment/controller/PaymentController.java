package edu.jennifer.ipayment.controller;


import edu.jennifer.ipayment.model.Transaction;
import edu.jennifer.ipayment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController{

	@Autowired
	private TransactionService transactionService;

	@RequestMapping("/makePayment")
	public Transaction makePayment(@RequestParam(name = "reservation_id") String reservation_id,
								   @RequestParam(name = "ammount") String ammount,
								   @RequestParam(name = "cardnumber") String cardnumber,
								   @RequestParam(name = "cardholder") String cardholder,
								   @RequestParam(name = "expire") String expire ){
		Transaction transaction = new Transaction();

		transaction.setReservationid(reservation_id);
		transaction.setAmmount(ammount);
		transaction.setCardNumber(cardnumber);
		transaction.setCardHolder(cardholder);
		transaction.setCardExpire(expire);

		return transactionService.save(transaction);

	}

}
