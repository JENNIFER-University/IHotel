package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ICheck Test Controller. Check connection to Credit Card Check Server
 */
@RestController
public class ICheckTestController{

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @RequestMapping("/icheck_test")
    public void doTest() {
        debug("Checking if Icheck is enabled .... ");
        debug("Icheck Settings " + Conf.getInstance().icheckEnabled());
        if(Conf.getInstance().icheckEnabled()){
            String icheckIP   = Conf.getInstance().getICheckIP();
            String icheckPort = Conf.getInstance().getICheckPort();
            debug("Making sample call to icheck. iCheck IP: " + icheckIP +" and Port: " + icheckPort);
            Validator v  = new Validator();
            boolean initialized = v.initialize(icheckIP,icheckPort);
            if(!initialized){
                debug("Failed to initialize the validator");
                return;
            }
            String cardNumber = "123456789";
            boolean result = v.checkCard(cardNumber);
            debug("Card Number " + cardNumber + " was sent to icheck and result is : " + result);
        }else{
            debug("iCheck is not enabled, please check app.conf to enable it");
        }

    }


    private void debug(String message){

        System.out.printf("[INFO] %s: %s\n",sdf.format(new Date()),message);
    }
}
