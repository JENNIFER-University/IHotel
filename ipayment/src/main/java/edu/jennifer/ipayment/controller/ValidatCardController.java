package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ValidatCardController{


	@RequestMapping("/validateCard")
	public CheckResult validate(@RequestParam(name = "cardNumber") String cardNumber){
		boolean useICheck = Conf.getInstance().icheckEnabled();
		CheckResult result = validateCard(cardNumber,useICheck);
		return result;
	}

	private CheckResult validateCard(String cardNumber,boolean useICheck){
		CheckResult result = new CheckResult();
		result.setCardNumber(cardNumber);
		if(useICheck){
			String IP   =  Conf.getInstance().getICheckIP();
			String port =  Conf.getInstance().getICheckPort();
			Validator validator = new Validator();
			validator.initialize(IP,port);
			int error = validator.checkCard(cardNumber) ? 0 : 1;
			result.setError(error);
		}else{
			int levels = cardNumber.length();
			if(levels < 5){
				levels = 10;
			}
			checkDigists(levels);

			if(cardNumber.startsWith("500"))
				result.setError(1);
			else
				result.setError(0);
		}
		return result;
	}

	private void checkDigists(int levels){
		if(levels == 0){
			return;
		}

		try{Thread.sleep(150);}catch(Exception ex){};
		checkDigists(levels-1);
	}


	class CheckResult{
		String cardNumber;
		int error;

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}


		public int getError() {
			return error;
		}

		public void setError(int error) {
			this.error = error;
		}
	}
}
