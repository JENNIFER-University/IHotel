package edu.jennifer.ipayment.service;

import edu.jennifer.ipayment.model.Transaction;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 2:17 PM.
 */
public interface TransactionService {

    Transaction save(Transaction transaction);

    Transaction findByReservationId(String reservationId);
}
