package edu.jennifer.check.service;

/**
 * @author Khalid Elshafie
 * @Created 9/15/17 2:06 PM.
 */
public class CheckTask {

    /**
     * Simulate checking card digists
     * @param number Credit Card Number
     * @return <strong>true</strong> if test passed, <strong>false</strong> otherwise.
     */
    public static boolean checkDigits(String number){
        String cardNumber = number.split("-")[0];
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

    /**
     * @param level
     * @param card
     * @return
     */
    public static boolean verifyCard(int level, String card){
        if (level == 0) {
            return checkDigits(card);
        }

        return verifyCard(--level, card);
    }
}
