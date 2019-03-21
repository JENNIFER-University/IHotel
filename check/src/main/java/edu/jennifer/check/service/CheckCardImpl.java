package edu.jennifer.check.service;

import edu.jennifer.common.AppUtil;
import edu.jennifer.icheck.ICheck;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Simple fake implementation of Credit Card Check
 * Created by khalid on 8/19/16.
 */
public class CheckCardImpl extends UnicastRemoteObject implements ICheck {

    /**
     * Default Constructor
     * @throws RemoteException remote exception
     */
    public CheckCardImpl() throws RemoteException{ }

    /**
     * Simulate checking if a credit card if valid or no.
     * @param cardNumber Credit Card Number
     * @return String <strong>valid</strong> if the credit card is valid, <strong>invalid</strong> if not
     * @throws RemoteException remote exception
     */
    public String checkCreditCard(String cardNumber) {
        boolean delayProcessing = cardNumber.split("-").length == 2;

        String result = isCardValid(cardNumber);

        if (delayProcessing) {
            validateCheckResult();
        }
        return result;
    }

    /**
     * Check if the card valid based on length
     * @param cardNumber The card number to be checked
     * @return String "valid" or "invalid"
     */
    private String isCardValid(String cardNumber) {
        int iterations = cardNumber.length();
        if (iterations > 10 || iterations == 0) {
            iterations = 10;
        }

        boolean isCardValid = CheckTask.verifyCard(iterations, cardNumber);
        return isCardValid ? "valid" : "invalid";
    }

    /**
     * Dummy method to slow down the processing logic
     */
    private void validateCheckResult() {
        long starTime = System.currentTimeMillis();
        final int PERIOD = AppUtil.getRandom(20000, 25000);
        for(int i =0; ; i++) {
            long now = System.currentTimeMillis();
            if (now - starTime >= PERIOD) break;
        }
    }

}
