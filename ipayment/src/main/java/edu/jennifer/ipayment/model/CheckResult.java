package edu.jennifer.ipayment.model;

/**
 * @author Khalid Elshafie
 * @Created 9/12/17 10:42 AM.
 */
public class CheckResult {

    private String cardNumber;
    private  int error;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
