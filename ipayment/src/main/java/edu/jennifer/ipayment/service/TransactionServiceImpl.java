package edu.jennifer.ipayment.service;

import edu.jennifer.ipayment.model.Transaction;
import edu.jennifer.ipayment.repo.TransactionRepository;
import org.springframework.stereotype.Service;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 2:18 PM.
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Iterable<Transaction> list() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(long id) {
        return transactionRepository.findOne(id);
    }

    @Override
    public Transaction findByReservationId(String reservationId) {
        return transactionRepository.findByReservationid(reservationId);
    }
}
