package edu.jennifer.ihotel.action;

import edu.jennifer.ihotel.model.User;

/**
 * @author Khalid Elshafie
 * @Created 9/15/17 12:16 PM.
 */
public class SignupAction extends BaseAction{

    private String realName;
    private String userName;
    private String password;

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setUsername(getPassword());
        user.setPassword(getPassword());
        user.setRealName(getRealName());
        if (getUserDAO().save(user))
            return SUCCESS;
        else
            return ERROR;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
