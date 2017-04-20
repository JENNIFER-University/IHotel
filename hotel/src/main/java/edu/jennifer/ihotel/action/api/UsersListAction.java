package edu.jennifer.ihotel.action.api;

import edu.jennifer.ihotel.action.BaseAction;
import edu.jennifer.ihotel.model.User;

import java.util.List;

/**
 * Created by khalid on 4/20/17.
 */
public class UsersListAction extends BaseAction {

    private List<User> users;

    @Override
    public String execute() throws Exception {
        setUsers(getUserDAO().findAll());
        return super.execute();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
