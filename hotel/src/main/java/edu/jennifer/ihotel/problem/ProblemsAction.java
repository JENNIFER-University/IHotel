package edu.jennifer.ihotel.problem;

import edu.jennifer.ihotel.action.BaseAction;

import java.util.ArrayList;
import java.util.List;


//TODO: Remove Me
/**
 * @author Khalid Elshafie
 * @Created 9/14/17 11:28 AM.
 */
public class ProblemsAction extends BaseAction {

    private List<Problem> problemList;

    public ProblemsAction() {
        problemList = ProblemPool.getInstance().getProblemList();

    }

    public List<Problem> getProblemList() { return problemList; }
}
