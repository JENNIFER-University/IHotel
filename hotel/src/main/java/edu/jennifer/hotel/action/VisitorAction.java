package edu.jennifer.hotel.action;

import edu.jennifer.hotel.util.VisitorCounterHelper;

/**
 * @author Khalid
 * @Created 2/18/19 5:00 PM.
 */
public class VisitorAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        VisitorCounterHelper visitorCounterHelper = VisitorCounterHelper.getInstance();
        visitorCounterHelper.increment();
        return SUCCESS;
    }
}
