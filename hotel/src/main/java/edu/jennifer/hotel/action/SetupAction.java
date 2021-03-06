package edu.jennifer.hotel.action;

import edu.jennifer.hotel.util.Conf;

/**
 * @author Khalid
 * @Created 12/28/17 1:20 PM.
 */
public class SetupAction extends BaseAction {

    private String ipaymentIp;
    private String  ipaymebtPort;

    @Override
    public String execute() throws Exception {
        Conf conf = Conf.getInstance();
        setIpaymebtPort(conf.getProperty(Conf.KEY_IPAYMENT_PORT));
        setIpaymentIp(conf.getProperty(Conf.KEY_IPAYMENT_IP));
        return SUCCESS;
    }

    public String getIpaymentIp() {
        return ipaymentIp;
    }

    public void setIpaymentIp(String ipaymentIp) {
        this.ipaymentIp = ipaymentIp;
    }

    public String getIpaymebtPort() {
        return ipaymebtPort;
    }

    public void setIpaymebtPort(String ipaymebtPort) {
        this.ipaymebtPort = ipaymebtPort;
    }
}
