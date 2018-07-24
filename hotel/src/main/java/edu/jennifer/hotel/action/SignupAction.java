package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.User;

/**
 * @author Khalid
 * @Created 9/15/17 12:16 PM.
 */
public class SignupAction extends BaseAction{

    private String realName;
    private String userName;
    private String password;

    @Override
    public String execute() throws Exception {
        if (!getUserDAO().isUsernameUnique(getUserName())) {
            addActionError("Username already exists.");
            return ERROR;
        }

        User user = new User();
        user.setUsername(getPassword());
        user.setPassword(getPassword());
        user.setRealName(getRealName());

        if (getUserDAO().save(user)) {
            return SUCCESS;
        }else {
            addActionError("Failed to create account. Please try again");
            return ERROR;
        }
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
