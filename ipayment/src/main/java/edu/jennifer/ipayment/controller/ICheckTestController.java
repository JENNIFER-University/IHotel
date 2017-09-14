package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ICheck Test Controller. Check connection to Credit Card Check Server
 */
@RestController
public class ICheckTestController{

    private Logger logger = LoggerFactory.getLogger(ICheckTestController.class);

    @Autowired
    private Conf config;

    @RequestMapping("/icheck_test")
    public Map<String, String> doTest() {

        Map<String, String> result = new HashMap<>();

        logger.info("iCheck Enabled = " + config.isIcheckEnabled());
        if(config.isIcheckEnabled()){
            String icheckIP   = config.getIcheckIp();
            String icheckPort = config.getIcheckPort();

            result.put("icheck_enabled", "true");
            result.put("icheck_ip", icheckIP);
            result.put("icheck_port", icheckPort);

            logger.info(String.format("Making a sample call to icheck. iCheck IP = [%s], Port = [%s]",icheckIP, icheckPort));
            Validator v  = new Validator();
            boolean initialized = v.initialize(icheckIP,icheckPort);
            if(!initialized){
                logger.info("Failed to initialize the validator");
                result.put("error", "Failed to initalize validator");
            }else {
                String cardNumber = "123456789";
                boolean validateResult = v.checkCard(cardNumber);
                logger.info("Card Number " + cardNumber + " was sent to icheck and result is : " + result);
                result.put("test_result", "passed");
            }
        }else{
            result.put("icheck_enabled", "false");
            logger.info("iCheck is not enabled, please check app.conf to enable it");
        }

        return result;
    }

}
