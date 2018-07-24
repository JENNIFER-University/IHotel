package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.User;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @author khalid
 * @created 07/04/2017.
 */
public class LoginAction extends BaseAction implements SessionAware{


    private String username;
    private String password;
    private String profile;

    private SessionMap<String, String> sessionMap;


    @Override
    public String execute(){
        if (getSimula() == 1) {
            initalize();
        }

        User userData = getUserDAO().login(getUsername(), getPassword());
        if(userData != null) {
            sessionMap.put("isLoggedIn","true");
            sessionMap.put("currentUser",userData.getUsername());
            return SUCCESS;
        }

        addActionError("Invalid Username/Password");
        return ERROR;
    }


    public String logout(){
        sessionMap.invalidate();
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
