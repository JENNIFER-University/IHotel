package edu.jennifer.ipayment.model;

/**
 * @author khalid
 * @version Created: 09/03/2017.
 */
public class DAOFactory {

    public static TransactionsDAO getTransactionDAO(){
        return new TransactionsDAO();
    }
}
