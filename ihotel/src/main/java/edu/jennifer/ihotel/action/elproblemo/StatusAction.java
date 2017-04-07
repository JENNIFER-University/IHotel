package edu.jennifer.ihotel.action.elproblemo;

import edu.jennifer.ihotel.action.BaseAction;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by khalid on 07/04/2017.
 */
public class StatusAction extends BaseAction {

    private String jsonString;

    @Override
    public String execute() throws Exception {
        JSONObject object = new JSONObject();
        try{
            object.put("status", "alive");
        }catch (JSONException ex){}
        setJsonString(object.toString());
        return  SUCCESS;

    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }
}
