package edu.jennifer.check.service;

import edu.jennifer.icheck.ICheck;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 * Sample implementation of Credt Check
 * Created by khalid on 8/19/16.
 */
public class CheckCardImpl extends UnicastRemoteObject implements ICheck {

    /**
     * Default Constructor
     * @throws RemoteException
     */
    public CheckCardImpl() throws RemoteException{}

    /**
     * Simulate checking if a credit card if valid or no.
     * @param cardNumber Credit Card Number
     * @return String <strong>valid</strong> if the credit card is valid, <strong>invalid</strong> if not
     * @throws RemoteException
     */
    public String checkCreditCard(String cardNumber) throws RemoteException {
        System.out.printf("Checking [%s] \n",cardNumber);
        if(checkDigits(cardNumber)){
            isBlackListed(cardNumber);
            return "valid";
        }else {
            System.out.println("Card Digits Check Failed");
            return "invalid";
        }
    }

    /**
     * Simulate checking card digists
     * @param cardNumber Credit Card Number
     * @return <strong>true</strong> if test passed, <strong>false</strong> otherwise.
     */
    private boolean checkDigits(String cardNumber){
        int sum = 0;
        for(int i = cardNumber.length() - 1; i >= 0; i--){
            int n = Integer.parseInt(cardNumber.substring(i,i+1));
            sum += n;
            try{Thread.sleep(150);}catch (InterruptedException ex){};
        }

        if(cardNumber.startsWith("500"))
            return false;
        else
            return sum > 10;
    }

    private boolean isBlackListed(String card){
        try{
            System.out.println("Checking Black listed credit card db");
            String query = "SELECT * FROM CARDS WHERE CARD_NUMBER = ?";
            Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, card);
            ResultSet rs = pst.executeQuery();
            boolean blackListed = false;
            if(rs.next()){
                blackListed = true;
            }
            rs.close();
            pst.close();
            return  blackListed;
        }catch (Exception ex){
            return false;
        }
    }
}
