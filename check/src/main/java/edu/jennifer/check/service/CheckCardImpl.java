package edu.jennifer.check.service;

import edu.jennifer.check.queue.Observable;
import edu.jennifer.check.queue.QueueManager;
import edu.jennifer.icheck.ICheck;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Sample implementation of Credit Card Check
 * Created by khalid on 8/19/16.
 */
public class CheckCardImpl extends UnicastRemoteObject implements ICheck, Observable {

    private QueueManager queueManager = QueueManager.getInstnace();
    private String status;
    private boolean wasUsingQueue = false;

    /**
     * Default Constructor
     * @throws RemoteException
     */
    public CheckCardImpl() throws RemoteException{
        queueManager.register(this);
    }

    /**
     * Simulate checking if a credit card if valid or no.
     * @param cardNumber Credit Card Number
     * @return String <strong>valid</strong> if the credit card is valid, <strong>invalid</strong> if not
     * @throws RemoteException
     */
    public String checkCreditCard(String cardNumber) throws RemoteException {
        boolean useQueue = cardNumber.split("-").length == 2;
        if(useQueue) {
            wasUsingQueue = true;
            return checkUsingQueue(cardNumber);
        }else {
            if (wasUsingQueue) {
                wasUsingQueue = false;
                queueManager.releaseAll();
            }
            return checkWithoutQueue(cardNumber);
        }
    }

    /**
     * Change the processing logic to use Queue manager and cause Service Queue
     * @param cardNumber
     * @return
     */
    private String checkUsingQueue(String cardNumber) {
        queueManager.add(cardNumber);
        waitForResultFromQueue();
        String checkResult= status;
        status = null;
        return checkResult;
    }

    /**
     * Delay waiting the result from the queue.
     */
    private void waitForResultFromQueue() {
        long starTime = System.currentTimeMillis();
        while (status == null) {
            for(int i =0; ; i++) {
                long now = System.currentTimeMillis();
                if (now - starTime >= 1000) break;
            }

            //Attempt to escape the service queue issue
            if (queueManager.isEmpty()) {
                status = "valid";
            }
        }
    }

    /**
     * Check card directly without queue maanager
     * @param card
     * @return
     */
    private String checkWithoutQueue(String card) {
        if(CheckTask.checkDigits(card)){
            int iterations = card.length();
            if (iterations > 10 || iterations == 0) {
                iterations = 10;
            }
            CheckTask.isBlackListed(iterations, card);
            return "valid";
        }else {
            System.out.printf("[%s] Card Digits Check Failed%n", getClass().getSimpleName());
            return "invalid";
        }
    }


    @Override
    public void update(String staus) {
        this.status = staus;
    }
}
