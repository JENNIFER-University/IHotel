package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name="ICheckTestController" ,urlPatterns={"/icheck_test"})
public class ICheckTestController extends BaseController{

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
