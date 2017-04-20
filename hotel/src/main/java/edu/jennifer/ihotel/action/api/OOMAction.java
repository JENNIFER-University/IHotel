package edu.jennifer.ihotel.action.api;

import edu.jennifer.ihotel.action.BaseAction;

import java.util.ArrayList;

/**
 * Created by khalid on 07/04/2017.
 */
public class OOMAction extends BaseAction{


    @Override
    public String execute() throws Exception {
        long start = System.currentTimeMillis();
        byte[] data = new byte[2^20];
        ArrayList<byte[]> buffer = new ArrayList<>();
        boolean running = true;
        try{
            while(running) {
                buffer.add(data);
            }

        }catch (OutOfMemoryError e){
            long end = System.currentTimeMillis() - start;
            System.out.printf("[%s] in [%d]ms\n",e.getMessage(), end);
            throw e;
        }

        return SUCCESS;

    }
}
