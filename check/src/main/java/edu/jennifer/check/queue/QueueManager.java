package edu.jennifer.check.queue;

import edu.jennifer.check.service.CheckTask;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Khalid Elshafie
 * @Created 9/15/17 12:42 PM.
 */
public class QueueManager extends Thread implements Subject{

    private static QueueManager instnace;
    private Observable observable;
    private Queue<String> cardToBeProcessQueue;
    private String cardStatus;
    private volatile boolean isRunning = true;

    public static QueueManager getInstnace() {
        if(instnace == null) {
            instnace = new QueueManager();
            instnace.start();
        }
        return instnace;
    }

    private QueueManager() {
        cardToBeProcessQueue = new LinkedList<>();
    }

    public void add(String card) {
        cardToBeProcessQueue.add(card);
    }


    @Override
    public void run() {
        System.out.printf("[%s] Cards Processing Queue is ready for action%n", getClass().getSimpleName());
        while (isRunning) {
            String result =null;
            if(!cardToBeProcessQueue.isEmpty()) {
                String card  = cardToBeProcessQueue.poll();
                if(CheckTask.checkDigits(card)){
                    CheckTask.isBlackListed(card.length(), card);
                    result = "valid";
                }else {
                    System.out.printf("[%s] Card Digits Check Failed%n", getClass().getSimpleName());
                    result = "invalid";
                }

                validateAndSetResult(result);
            }

            try { Thread.sleep(1000); } catch (InterruptedException ex) {}
        }
    }

    private void validateAndSetResult(String result) {
        if (result == null) result = "valid";

        try {
            Thread.sleep(6000);
        }catch (InterruptedException ex){}


        setCardStatus(result);
    }


    @Override
    public void register(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void notifyProcessingComplete() {
        this.observable.update(cardStatus);
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
        notifyProcessingComplete();
    }

    public void releaseAll() {
        isRunning = false;
        cardToBeProcessQueue.clear();
    }

    public boolean isEmpty(){
        return cardToBeProcessQueue.isEmpty();
    }
}
