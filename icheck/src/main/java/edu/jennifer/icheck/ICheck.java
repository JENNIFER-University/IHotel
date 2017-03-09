package edu.jennifer.icheck;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by khalid on 9/7/16.
 */
public interface ICheck extends Remote{

    public String checkCreditCard(String cardNumber) throws RemoteException;
}
