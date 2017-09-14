package edu.jennifer.ihotel.problem;

import edu.jennifer.ihotel.action.BaseAction;
import edu.jennifer.ihotel.model.User;

import java.util.ArrayList;
import java.util.List;


//TODO: Remove Me
/**
 * @author Khalid Elshafie
 * @Created 9/14/17 11:28 AM.
 */
public class ProblemsAction extends BaseAction {

    private List<Problem> problemList;

    /**
     * Used for the Slow Login
     */
//    private List<User> userList;

    public ProblemsAction() {
        problemList = ProblemPool.getInstance().getProblemList();
//        userList = getUserDAO().findAll();

    }

    public List<Problem> getProblemList() { return problemList; }
//    public List<User> getUserList() { return userList;}
}
