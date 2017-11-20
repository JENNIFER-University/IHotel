package edu.jennifer.ihotel.problem;

import edu.jennifer.ihotel.action.BaseAction;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;

/**
 * @author Khalid Elshafie
 * @Created 9/14/17 4:32 PM.
 */
public class ToggleProblemAction extends BaseAction {

    private int id;

    public void updateConfig() {
        ProblemPool.getInstance().toggleProblem(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
