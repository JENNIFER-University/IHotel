package edu.jennifer.payment.controller;

import edu.jennifer.payment.util.Conf;
import edu.jennifer.payment.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khalid on 4/20/17.
 */
@RestController
public class StatusController {

    @Autowired
    private Conf config;

    @RequestMapping("/status")
    public  Status healthCheck(){
        Status s = new Status();
        s.setIpayment("alive");

        if(config.isIcheckEnabled()){
            Validator v  = new Validator();
            boolean initialized = v.initialize(config.getIcheckIp(),config.getIcheckPort());
            if(!initialized){
                s.setIcheck("dead");
            }else{
                String cardNumber = "123456789";
                boolean result = v.checkCard(cardNumber);
                s.setIcheck("alive");
            }
        }else{
            s.setIcheck("disabled");
        }


        return  s;

    }

    private class Status{
        private String ipayment;
        private String icheck;

        public void setIpayment(String ipayment) {
            this.ipayment = ipayment;
        }

        public void setIcheck(String icheck) {
            this.icheck = icheck;
        }

        public String getIcheck() {
            return icheck;
        }

        public String getIpayment() {
            return ipayment;
        }
    }
}
