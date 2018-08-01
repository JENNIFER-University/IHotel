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

    private final String VALID = "valid";
    private final String INVALID = "invalid";

    /**
     * Default Constructor
     * @throws RemoteException
     */
    public CheckCardImpl() throws RemoteException{ }

    /**
     * Simulate checking if a credit card if valid or no.
     * @param cardNumber Credit Card Number
     * @return String <strong>valid</strong> if the credit card is valid, <strong>invalid</strong> if not
     * @throws RemoteException
     */
    public String checkCreditCard(String cardNumber) throws RemoteException {
        boolean delayProcessing = cardNumber.split("-").length == 2;

        String result = isCardValid(cardNumber);

        if (delayProcessing) {
            validateCheckResult();
        }
        return result;
    }

    private String isCardValid(String cardNumber) {
        int iterations = cardNumber.length();
        if (iterations > 10 || iterations == 0) {
            iterations = 10;
        }

        boolean isCardValid = CheckTask.verifyCard(iterations, cardNumber);
        return isCardValid ? VALID : INVALID;
    }

    private void validateCheckResult() {
        long starTime = System.currentTimeMillis();
        final int PERIOD = AppUtil.getRandom(20000, 25000);
        for(int i =0; ; i++) {
            long now = System.currentTimeMillis();
            if (now - starTime >= PERIOD) break;
        }
    }

}
