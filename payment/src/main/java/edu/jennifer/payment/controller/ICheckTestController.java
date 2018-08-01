package edu.jennifer.payment.controller;

import edu.jennifer.payment.util.Conf;
import edu.jennifer.payment.util.Validator;
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

    @Autowired
    private Conf config;

    Logger ILogger = LoggerFactory.getLogger(ICheckTestController.class);


    @RequestMapping("/icheck_test")
    public Map<String, String> doTest() {

        Map<String, String> result = new HashMap<>();

        ILogger.info(String.format("iCheck Enabled = " , config.isIcheckEnabled()));
        if(config.isIcheckEnabled()){
            String icheckIP   = config.getIcheckIp();
            String icheckPort = config.getIcheckPort();

            result.put("icheck_enabled", "true");
            result.put("icheck_ip", icheckIP);
            result.put("icheck_port", icheckPort);

            ILogger.info(String.format("Making a sample call to iCheck. iCheck IP = [%s], Port = [%s]",icheckIP, icheckPort));
            Validator v  = new Validator();
            boolean initialized = v.initialize(icheckIP,icheckPort);
            if(!initialized){
                ILogger.warn("Failed to initialize the validator");
                result.put("error", "Failed to initalize validator");
            }else {
                String cardNumber = "123456789";
                boolean validateResult = v.checkCard(cardNumber);
                ILogger.info(String.format("Card Number [%s] was sent to iCheck and the result is [%b]", cardNumber, validateResult));
                result.put("test_result", "passed");
            }
        }else{
            result.put("icheck_enabled", "false");
            ILogger.info("iCheck is not enabled, please check app.conf to enable it");
        }

        return result;
    }

}
