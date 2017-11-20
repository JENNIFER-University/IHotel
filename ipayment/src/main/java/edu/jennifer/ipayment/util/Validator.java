package edu.jennifer.ipayment.util;


import edu.jennifer.icheck.ICheck;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by khalid on 8/25/16.
 */
public class Validator {

    private ICheck icheck;

    public boolean initialize(String serverIp,String portString){
        try{
            int port = Integer.parseInt(portString);
            Registry registry = LocateRegistry.getRegistry(serverIp,port);
            icheck = (ICheck) registry.lookup("CheckCard");
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    public boolean checkCard(String cardNumber){
        try{
            return icheck.checkCreditCard(cardNumber).equals("valid");
        }catch (RemoteException ex){
            return false;
        }
    }



}
