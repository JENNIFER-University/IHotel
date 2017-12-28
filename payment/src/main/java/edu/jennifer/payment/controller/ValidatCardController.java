package edu.jennifer.payment.controller;

import edu.jennifer.payment.model.CheckResult;
import edu.jennifer.payment.util.Conf;
import edu.jennifer.payment.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ValidatCardController{

	@Autowired
	private Conf config;

	@RequestMapping("/validateCard")
	public CheckResult validate(@RequestParam(name = "cardNumber") String cardNumber){
		boolean useICheck =  config.isIcheckEnabled();
		CheckResult result = validateCard(cardNumber,useICheck);
		return result;
	}

	private CheckResult validateCard(String cardNumber,boolean useICheck){
		CheckResult result = new CheckResult();
		result.setCardNumber(cardNumber);
		if(useICheck){
			Validator validator = new Validator();
			validator.initialize(config.getIcheckIp(),config.getIcheckPort());
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

}
