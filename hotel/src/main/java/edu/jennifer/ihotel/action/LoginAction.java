package edu.jennifer.ihotel.action;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by khalid on 07/04/2017.
 */
public class LoginAction extends BaseAction implements SessionAware{

    private String username;
    private String password;
    private String profile;

    private SessionMap<String, String> sessionMap;


    @Override
    public String execute() throws Exception {
        String userData = getUserDAO().login(getUsername(), getPassword(), getProfile());
        if(userData != null) {
            sessionMap.put("isLoggedIn","true");
            sessionMap.put("currentUser",userData);
            return SUCCESS;
        }
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

    public String getProfile() {
        return profile;
    }
}
