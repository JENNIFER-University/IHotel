package edu.jennifer.stress.model;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 12/29/17 11:33 AM.
 */
@Parameters(separators = "=")
public class CliParams {

    @Parameter(names = { "-h", "--help"}, description = "Display the help information", help = true)
    private boolean help;

    @Parameter(names = { "-i", "--ip"}, description = "Specify iHotel IP Address", required = true)
    private String ihotelIp;

    @Parameter(names = { "-p", "--port"}, description = "Specify iHotel IP port", required = true)
    private int ihotelPort;

    @Parameter(names = { "-u", "--users"}, description = "Maximum number of users")
    private int maxUsers = 50;

    @Parameter(names = { "-d", "--debug"}, description = "Enable Debug Mode")
    private boolean debug;

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public String getIhotelIp() {
        return ihotelIp;
    }

    public void setIhotelIp(String ihotelIp) {
        this.ihotelIp = ihotelIp;
    }

    public int getIhotelPort() {
        return ihotelPort;
    }

    public void setIhotelPort(int ihotelPort) {
        this.ihotelPort = ihotelPort;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    @Override
    public String toString() {
        return "CliParams{" +
                "help=" + help +
                ", ihotelIp='" + ihotelIp + '\'' +
                ", ihotelPort=" + ihotelPort +
                ", maxUsers=" + maxUsers +
                ", debug=" + debug +
                '}';
    }
}
