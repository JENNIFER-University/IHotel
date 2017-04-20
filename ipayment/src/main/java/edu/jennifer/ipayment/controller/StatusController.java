package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khalid on 4/20/17.
 */
@RestController
public class StatusController {

    @RequestMapping("/status")
    public @ResponseBody
    Status healthCheck(){
        Status s = new Status();
        s.setIpayment("alive");
        if(Conf.getInstance().icheckEnabled()){
            String icheckIP   = Conf.getInstance().getICheckIP();
            String icheckPort = Conf.getInstance().getICheckPort();
            Validator v  = new Validator();
            boolean initialized = v.initialize(icheckIP,icheckPort);
            if(!initialized){
                s.setIcheck("dead");
            }else{
                String cardNumber = "123456789";
                boolean result = v.checkCard(cardNumber);
                s.setIcheck("alive");
            }
        }else{
            s.setIcheck("dead");
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
