package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.User;
import edu.jennifer.hotel.util.Conf;

/**
 * Save the Configurations
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 12/28/17 12:37 PM.
 */
public class SetupSubmitAction extends BaseAction{

    private String ipaymentIp;
    private String  ipaymebtPort;

    @Override
    public String execute() throws Exception {
        Conf.getInstance().saveProperty(Conf.KEY_IPAYMENT_IP, getIpaymentIp());
        Conf.getInstance().saveProperty(Conf.KEY_IPAYMENT_PORT, getIpaymebtPort());
        Conf.getInstance().reload();
        //Just assume it will be saved :)

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
