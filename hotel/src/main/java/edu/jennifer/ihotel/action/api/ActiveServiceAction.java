package edu.jennifer.ihotel.action.api;

import edu.jennifer.ihotel.action.BaseAction;

import java.io.File;
import java.io.IOException;

/**
 * Created by khalid on 07/04/2017.
 */
public class ActiveServiceAction extends BaseAction{

    private String cmd;


    @Override
    public String execute() throws Exception {
        if(getCmd() == null || getCmd().length() == 0) {
            return SUCCESS;
        }

        if(getCmd().equals("1")){
            FileManager.getInstance().writeToFile();
        }else if(getCmd().equals("2")){
            try{
                new File(System.getProperty("java.io.tmpdir"), FileManager.UNLOCK_FILE_NAME).createNewFile();
            }catch (IOException ex){
                System.out.println("OOOOPS, failed to create release signal, please create it manually using command [touch end_service_queue.tmp] in tomcat tmp dir");
            }
        }
        return SUCCESS;
    }

    public String readFile(){
        FileManager.getInstance().readTheFile();
        return SUCCESS;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}
